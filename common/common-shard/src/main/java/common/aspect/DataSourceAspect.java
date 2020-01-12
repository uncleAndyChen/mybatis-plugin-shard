package common.aspect;

import common.shard.ShardRequest;
import common.shard.ShardConfig;
import common.shard.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.List;

public class DataSourceAspect {
    public static ShardConfig shardConfig;
    private final String incorrectShardSchemaKey = "incorrect shard schema key:%s";

    public void setShardTableConfigView(ShardConfig shardConfig) {
        DataSourceAspect.shardConfig = shardConfig;
    }

    /**
     * 分库策略
     * 1. 如果第一个参数是 ShardRequest 类型，并且指定了一个存在的数据源 key，则使用这个数据源
     * 2. 通过注解的方式指定了正确的数据源，则使用这个数据源
     * 3. biz.service 接口类名如果配置在某一个数据源 key 下的接口名列表中（ShardConfig.shardSchemaInterfaceClassNameList），则使用这个数据源
     * 4. 以上三类都不符合，则使用默认数据源
     */
    public void before(JoinPoint point) throws Exception {
        if (isAppointSchemaKey(point)) {
            return;
        }

        Object target = point.getTarget();
        Class<?>[] classes = target.getClass().getInterfaces();

        if (isSetCorrectDbSourceFromAnnotation(point, classes)) {
            return;
        }

        setTargetDataSourceFromBizServiceInterfaceName(classes);
    }

    public void afterHandler() {
        HandleDataSource.removeVariables();
    }

    /**
     * 如果第一个参数是 ShardRequest 类型，并且指定了一个存在的数据源 key，则使用这个数据源
     */
    private boolean isAppointSchemaKey(JoinPoint point) throws Exception {
        Object[] args = point.getArgs();

        if (args.length == 0 || !(args[0] instanceof ShardRequest)) {
            return false;
        }

        ShardRequest shardRequest = (ShardRequest) args[0];

        if (shardRequest.getShardKeySchema().length() == 0) {
            return false;
        }

        if (isIncorrectSchemaKey(shardRequest.getShardKeySchema())) {
            throw new Exception(String.format(incorrectShardSchemaKey, shardRequest.getShardKeySchema()));
        }

        HandleDataSource.putSchemaKey(shardRequest.getShardKeySchema());
        HandleDataSource.setShardRequest(shardRequest);
        return true;
    }

    /**
     * biz.service 接口类如果配置在某一个数据源下，则使用这个数据源
     */
    private void setTargetDataSourceFromBizServiceInterfaceName(Class<?>[] classes) {
        for (String key : shardConfig.schemaKeyList) {
            if (isShardSchemaInterfaceClassNameListContainsServiceInterfaceClassName(classes[0].getName(), key)) {
                return;
            }
        }
    }

    /**
     * 检查当前 biz.service 接口类是否包含在给定的数据源配置里
     *
     * @param className   服务接口类的全路径名称
     * @param dbSourceKey 数据源配置 key
     */
    private boolean isShardSchemaInterfaceClassNameListContainsServiceInterfaceClassName(String className, String dbSourceKey) {
        List<String> schemaInterfaceNameList = DataSourceAspect.shardConfig.shardSchemaInterfaceClassNameList.get(dbSourceKey);

        if (schemaInterfaceNameList == null) {
            return false;
        }

        if (schemaInterfaceNameList.contains(className)) {
            HandleDataSource.putSchemaKey(dbSourceKey);
            return true;
        }

        return false;
    }

    /**
     * 通过注解的方式指定了正确的数据源，则返回 true
     * 方法注解优先判断，其次是类的注解
     */
    private boolean isSetCorrectDbSourceFromAnnotation(JoinPoint point, Class<?>[] classes) throws Exception {
        String method = point.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        TargetDataSource targetDataSource;

        Method m = classes[0].getMethod(method, parameterTypes);

        if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
            targetDataSource = m.getAnnotation(TargetDataSource.class);

            if (isIncorrectSchemaKey(targetDataSource.schemaKey())) {
                throw new Exception(String.format(incorrectShardSchemaKey, targetDataSource.schemaKey()));
            }

            HandleDataSource.putSchemaKey(targetDataSource.schemaKey());
            return true;
        }

        if (classes[0].isAnnotationPresent(TargetDataSource.class)) {
            targetDataSource = classes[0].getAnnotation(TargetDataSource.class);

            if (isIncorrectSchemaKey(targetDataSource.schemaKey())) {
                throw new Exception(String.format(incorrectShardSchemaKey, targetDataSource.schemaKey()));
            }

            HandleDataSource.putSchemaKey(targetDataSource.schemaKey());
            return true;
        }

        return false;
    }

    /**
     * 检查是否为错误的数据源 key
     * 如果不在配置的数据源 schemaKeyList 里，则返回 true
     */
    private boolean isIncorrectSchemaKey(String shardKeySchema) {
        for (String keySchema : DataSourceAspect.shardConfig.schemaKeyList) {
            if (shardKeySchema.equals(keySchema)) {
                return false;
            }
        }

        return true;
    }
}