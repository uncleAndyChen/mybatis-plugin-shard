package common.aspect;

import common.shard.ShardView;

public class HandleDataSource {
    private static final ThreadLocal<String> schemaKey = new ThreadLocal<>();
    private static final ThreadLocal<ShardView> shardViewThreadLocal = new ThreadLocal<>();

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

    public static void setShardView(ShardView shardView) {
        shardViewThreadLocal.set(shardView);
    }

    public static void removeVariables() {
        shardViewThreadLocal.remove();
        schemaKey.remove();
    }

    public static ShardView getShardView() {
        return shardViewThreadLocal.get();
    }
}