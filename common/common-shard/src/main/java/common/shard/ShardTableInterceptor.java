package common.shard;

import common.aspect.HandleDataSource;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class ShardTableInterceptor implements Interceptor {
    private static final ObjectFactory objectFactory = new DefaultObjectFactory();
    private static final ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory reflectorFactory = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory, reflectorFactory);

        doShardTable(metaStatementHandler);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        if (properties.containsKey("shardTableByKeyDirectlyTables")) {
            setListByStringSource(ShardTableConfigView.shardTableByKeyDirectlyList, properties.get("shardTableByKeyDirectlyTables").toString());
        }

        if (properties.containsKey("shardTableByKeyDivideByTables")) {
            setListByStringSource(ShardTableConfigView.shardTableByKeyDivideByList, properties.get("shardTableByKeyDivideByTables").toString());
        }

        if (properties.containsKey("divideByValue")) {
            String divideByValueConfig = properties.get("divideByValue").toString();

            if (divideByValueConfig.length() > 0) {
                try {
                    ShardTableConfigView.divideByValue = Integer.parseInt(divideByValueConfig);
                } catch (Exception e) {
                    throw new IllegalArgumentException("convert divideByValue to int failed, please check.");
                }
            }
        }

        if (properties.containsKey("isPrintShardSqlInfo")) {
            ShardTableConfigView.isPrintShardSqlInfo = properties.get("isPrintShardSqlInfo").toString().equals("true");
        } else {
            ShardTableConfigView.isPrintShardSqlInfo = false;
        }
    }

    private void doShardTable(MetaObject metaStatementHandler) {
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        if (originalSql == null || originalSql.equals("") || ShardTableConfigView.notNeedShardSqlList.contains(originalSql)) {
            return;
        }

        ShardRequest shardRequest = HandleDataSource.getShardRequest();

        if (shardRequest == null) {
            return;
        }

        if ((shardRequest.getShardKeyTable() == null || shardRequest.getShardKeyTable().length() == 0) && shardRequest.getShardKeyTableNumber() < 1) {
            return;
        }

        metaStatementHandler.setValue("delegate.boundSql.sql", ShardTablePolicy.getRealExecuteSql(originalSql, shardRequest));
    }

    private static void setListByStringSource(List<String> targetList, String sourceKeys) {
        if (sourceKeys.length() == 0) {
            return;
        }

        String[] keyArray = sourceKeys.split(",");
        targetList.addAll(Arrays.asList(keyArray));
    }
}
