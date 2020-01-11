package common.shard;

public class ShardRequest {
    /**
     * 分库标志 key，是定义数据源时指定的 key，在执行数据库操作之前，通过该 key 动态切换数据源。
     * 如果只是分库，只用这一个属性
     */
    private String shardKeySchema;

    /**
     * 分表标志 key，直接用作分表后缀的 key 值
     *      如应用该规则的原始表名为 table_name，则对应的分表为：table_name_key
     * 需要配合 MyBatis 插件 ShardTableInterceptor（与该类位于同一个目录）使用
     *     应用该规则的原始表名，在插件 ShardTableInterceptor 的属性 shardTableByKeyDivideByTables 下配置
     *          参见:https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/biz/biz-config/src/main/resources/mybatis-config.xml
     */
    private String shardKeyTable;

    /**
     * 动态分表参数编号，整形，一般与用户绑定
     * 需要配合 MyBatis 插件 ShardTableInterceptor（与该类位于同一个目录）使用
     *      应用该规则的原始表名，在插件 ShardTableInterceptor 的属性 shardTableByKeyDivideByTables 下配置
     *          参见:https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/biz/biz-config/src/main/resources/mybatis-config.xml
     * shardKeyTableNumber 除以 divideByValue 得到的值作为分表后缀的表
     *
     * 场景：SaaS 平台，每个用户分配一个编码值，可以按一定规则平均分配，比如现有有10万个用户，我们打算分10张表，那么，平均分配的话，就有意味着每一万个用户有一个编码值。
     * 用户分表编号
     * 如：针对 SaaS 的软件平台，是用户在初始化时分配的一个分表编号，可能多个商家共用一个编号，意味着这些商家共享一套表。
     * 再如：对于 SasS 的 VIP 用户，可以分配一个唯一的分表编号，这就意味着这个 VIP 用户独享一套表。
     * 多个用户的数据可能存在于同一个数据库实例，也可能存在于多个数据库实例。
     *
     * 用法：
     * 1. 如果 divideByValue 值大于 1，则除以这个数得到的结果作为后缀
     * 2. 否则直接用作后缀
     */
    private int shardKeyTableNumber;

    public ShardRequest(String shardKeySchema) {
        this.shardKeySchema = shardKeySchema;
    }

    public ShardRequest() {
        this.shardKeySchema = "";
        this.shardKeyTable = "";
        this.shardKeyTableNumber = -1;
    }

    public String getShardKeySchema() {
        return shardKeySchema;
    }

    public void setShardKeySchema(String shardKeySchema) {
        this.shardKeySchema = shardKeySchema;
    }

    public String getShardKeyTable() {
        return shardKeyTable;
    }

    public void setShardKeyTable(String shardKeyTable) {
        this.shardKeyTable = shardKeyTable;
    }

    public int getShardKeyTableNumber() {
        return shardKeyTableNumber;
    }

    public void setShardKeyTableNumber(int shardKeyTableNumber) {
        this.shardKeyTableNumber = shardKeyTableNumber;
    }
}
