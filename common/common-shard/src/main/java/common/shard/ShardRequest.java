package common.shard;

public class ShardRequest {
    /**
     * 分库标志 key，是定义数据源时指定的 key，在执行数据库操作之前，通过该 key 动态切换数据源。
     * 如果只是分库，除了用到个属性，还可利用 ShardTableConfig.shardSchemaInterfaceNameList 实现。
     *      有关这两项配置的详细信息，请参见：https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/biz/biz-config/src/main/resources/db-source.xml
     */
    private String shardKeySchema;

    /**
     * 分表标志 key，直接用作分表后缀的 key 值，针对直接添加后缀的表
     *      举例：应用该规则的原始表名为 table_name，则对应的分表为：table_name_key
     * 需要配合 ShardTableConfig 使用，与该类位于同一个目录，在 db-source.xml 中配置各属性值
     *     应用该规则的原始表名:ShardTableConfig.shardTableDirectlyList
     *          详细描述，请参见:https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/biz/biz-config/src/main/resources/db-source.xml
     */
    private String shardKeyTable;

    /**
     * 动态分表参数编号，整形，一般与用户绑定，针对需要除一个数得到后缀的表
     * 需要配合 ShardTableConfig 使用，与该类位于同一个目录，在 db-source.xml 中配置各属性值
     *     应用该规则的原始表名:ShardTableConfig.shardTableDivideList
     *          详细描述，请参见:https://github.com/uncleAndyChen/mybatis-plugin-shard/blob/master/biz/biz-config/src/main/resources/db-source.xml
     *
     * 场景：SaaS 平台，每个用户分配一个编码值，可以按一定规则平均分配，比如现有有10万个用户，我们打算分10张表，那么，平均分配的话，就意味着每一万个用户有一个分表编号。
     * 极端地，对于 SasS 的超级 VIP 用户，可以分配一个唯一的分表编号，这就意味着这个 VIP 用户独享一套表。
     * 多个用户的数据可能存在于同一个数据库实例，也可能存在于多个数据库实例，可根据业务灵活分配。
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
