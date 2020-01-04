package common.dal.aspect.shard;

import common.dal.aspect.source.HandleDataSource;
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
        System.out.println(properties);
    }

    private void doShardTable(MetaObject metaStatementHandler) {
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        if (originalSql == null || originalSql.equals("") || ShardTablePolicy.notNeedShardSqlList.contains(originalSql)) {
            return;
        }

        ShardView shardView = HandleDataSource.getShardView();

        if (shardView == null) {
            return;
        }

        if ((shardView.getShardKeyTable() == null || shardView.getShardKeyTable().length() == 0) && shardView.getShardKeyTableNumber() < 1) {
            return;
        }

        metaStatementHandler.setValue("delegate.boundSql.sql", ShardTablePolicy.getRealExecuteSql(originalSql, shardView));
    }
}
