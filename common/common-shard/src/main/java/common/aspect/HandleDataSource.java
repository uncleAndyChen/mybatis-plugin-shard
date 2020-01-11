package common.aspect;

import common.shard.ShardRequest;

public class HandleDataSource {
    private static final ThreadLocal<String> schemaKey = new ThreadLocal<>();
    private static final ThreadLocal<ShardRequest> shardRequestThreadLocal = new ThreadLocal<>();

    /**
     * 绑定当前线程数据源路由的key
     */
    public static void putSchemaKey(String schemaKeyParameter) {
        schemaKey.set(schemaKeyParameter);
    }

    /**
     * 获取当前线程的数据源路由的key
     */
    public static String getSchemaKey() {
        return schemaKey.get();
    }

    public static void setShardRequest(ShardRequest shardRequest) {
        shardRequestThreadLocal.set(shardRequest);
    }

    public static void removeVariables() {
        shardRequestThreadLocal.remove();
        schemaKey.remove();
    }

    public static ShardRequest getShardRequest() {
        return shardRequestThreadLocal.get();
    }
}