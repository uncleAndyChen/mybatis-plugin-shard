package common.dal.aspect.source;

import common.dal.aspect.shard.ShardRequest;
import common.dal.aspect.shard.ShardView;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DataSourceAspect {
    private static Properties dbShardProperties = new Properties();
    private static String[] allSchemaKeys;
    private final String incorrectShardSchemaKey = "incorrect shard schema key:%s";

    static {
        try {
            dbShardProperties = PropertiesLoaderUtils.loadAllProperties("db-shard.properties");
            allSchemaKeys = getValue("data.source.keys.all").split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dbShardProperties.size() == 0) {
            // 控制台输出中文可能会显示成乱码，所以异常信息也同时包含了英文
            throw new IllegalArgumentException("没有找到配置文件db-shard.properties，或该文件里没有配置项。" +
                    "请在这个文件里添加配置项，同时把文件放到 classpath 下，比如 resources 目录。\n" +
                    "配置信息请参照: https://github.com/uncleAndyChen/mybatis-plugin-shard \n" +
                    "not find db-shard.properties file, or no item in this file. " +
                    "please put config item in this file, " +
                    "the item include: \n" +
                    "data.source.key.{db source key}(should be more then one),\n" +
                    "data.source.keys.all,\n" +
                    "dal.includes.{db source key}(should be more then one)\n" +
                    "help: https://github.com/uncleAndyChen/mybatis-plugin-shard \n");
        }
    }

    /**
     * 1. 如果第一个参数是 ShardView 类型，并且指定了一个存在的数据源 key，则使用这个数据源
     * 2. 通过注解的方式指定了正确的数据源，则使用这个数据源
     * 3. Mapper 如果配置在某一个数据源下，则使用这个数据源
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

        setTargetDataSourceFromMappersConfig(classes);
    }

    public void afterHandler() {
        HandleDataSource.removeVariables();
    }

    /**
     * 如果第一个参数是 ShardView 类型，并且指定了一个存在的数据源 key，则使用这个数据源
     */
    private boolean isAppointSchemaKey(JoinPoint point) throws Exception {
        Object[] args = point.getArgs();

        if (args.length == 0 || !(args[0] instanceof ShardRequest)) {
            return false;
        }

        ShardRequest shardRequest = (ShardRequest) args[0];
        ShardView shardView = shardRequest.getShardView();

        if (shardView == null) {
            return false;
        }

        if (shardView.getShardKeySchema().length() == 0) {
            return false;
        }

        if (isIncorrectSchemaKey(shardView.getShardKeySchema())) {
            throw new Exception(String.format(incorrectShardSchemaKey, shardView.getShardKeySchema()));
        }

        HandleDataSource.putSchemaKey(shardView.getShardKeySchema());
        HandleDataSource.setShardView(shardView);
        return true;
    }

    /**
     * Mapper 如果配置在某一个数据源下，则使用这个数据源
     */
    private void setTargetDataSourceFromMappersConfig(Class<?>[] classes) {
        for (String key : allSchemaKeys) {
            if (isDbSourceConfigContainsMapperClass(classes[0].getName(), key)) {
                return;
            }
        }
    }

    /**
     * 检查当前 Mapper 是否包含在给定的数据源配置里
     *
     * @param className   Mapper 类
     * @param dbSourceKey 数据源配置
     */
    private boolean isDbSourceConfigContainsMapperClass(String className, String dbSourceKey) {
        String includeKey = getValue("biz.service." + dbSourceKey);

        if (includeKey.length() == 0) {
            return false;
        }

        if (includeKey.contains(className)) {
            HandleDataSource.putSchemaKey(dbSourceKey);
            return true;
        }

        return false;
    }

    /**
     * 检查是否为错误的数据源 key
     * 如果不在配置的数据源 allSchemaKeys 里，则返回 true
     */
    private boolean isIncorrectSchemaKey(String shardKeySchema) {
        for (String keySchema : allSchemaKeys) {
            if (shardKeySchema.equals(keySchema)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 通过注解的方式指定了正确的数据源，则返回 true
     * 方法注解优先判断，其次是类的注解
     */
    private boolean isSetCorrectDbSourceFromAnnotation(JoinPoint point, Class<?>[] classes) throws Exception {
        String method = point.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        TargetDataSource targetDataSource;

        try {
            Method m = classes[0].getMethod(method, parameterTypes);

            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                targetDataSource = m.getAnnotation(TargetDataSource.class);

                if (isIncorrectSchemaKey(targetDataSource.schemaKey())) {
                    throw new Exception(String.format(incorrectShardSchemaKey, targetDataSource.schemaKey()));
                }

                HandleDataSource.putSchemaKey(targetDataSource.schemaKey());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private static String getValue(String key) {
        return dbShardProperties.getProperty(key, "");
    }
}