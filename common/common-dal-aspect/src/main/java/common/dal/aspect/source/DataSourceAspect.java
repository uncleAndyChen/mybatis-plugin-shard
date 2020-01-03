package common.dal.aspect.source;

import common.model.ShardView;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DataSourceAspect {
    private static Properties dbShardProperties = new Properties();
    private static String[] notDefaultDBSourceKeys;

    static {
        try {
            dbShardProperties = PropertiesLoaderUtils.loadAllProperties("dbShard.properties");
            notDefaultDBSourceKeys = getValue("data.source.keys.not.default").split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return dbShardProperties.getProperty(key, "");
    }

    public void before(JoinPoint point) throws Exception {
        Object[] args = point.getArgs();

        if (args.length > 1) {
            if (args[0] == null) {
                return;
            }

            if (!(args[0] instanceof ShardView)) {
                throw new Exception("The first parameter must be the type of ShardView!");
            }

            ShardView shardView = (ShardView) args[0];

            if (shardView.getShardKeySchema().length() > 0) {
                if (!isCorrectShardKeySchema(shardView.getShardKeySchema())) {
                    throw new Exception("incorrect shard key of schema!");
                }

                HandleDataSource.putSchemaKey(shardView.getShardKeySchema());
                HandleDataSource.setShardView(shardView);
            }

            return;
        }

        Object target = point.getTarget();
        Class<?>[] classes = target.getClass().getInterfaces();

        for (String key : notDefaultDBSourceKeys) {
            if (isGeneralConfig(classes[0].getName(), key)) {
                return;
            }
        }

        String method = point.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();

        try {
            Method m = classes[0].getMethod(method, parameterTypes);

            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                HandleDataSource.putSchemaKey(data.value());
            } else {
                HandleDataSource.putSchemaKey(getValue("data.source.key.default"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afterHandler() {
        HandleDataSource.removeVariables();
    }

    private boolean isGeneralConfig(String className, String dbSourceKey) {
        String includeKey = getValue("mappers.includes." + dbSourceKey);

        if (includeKey.length() == 0) {
            return false;
        }

        if (includeKey.contains(className)) {
            HandleDataSource.putSchemaKey(dbSourceKey);
            return true;
        }

        return false;
    }

    private boolean isCorrectShardKeySchema(String shardKeySchema) {
        if (getValue("data.source.key.default").equals(shardKeySchema)) {
            return true;
        }

        for (String keySchema : notDefaultDBSourceKeys) {
            if (shardKeySchema.equals(keySchema)) {
                return true;
            }
        }

        return false;
    }
}