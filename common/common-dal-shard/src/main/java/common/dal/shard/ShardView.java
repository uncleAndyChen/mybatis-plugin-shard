package common.dal.shard;

public class ShardView {
    /**
     * 分库标志 key，是定义数据源时指定的 key，在执行数据库操作之前，通过该 key 动态切换数据源。
     */
    private String shardKeySchema;
    /**
     * 直接用作后缀的 key 值
     */
    private String shardKeyTable;
    /**
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

    public ShardView(String shardKeySchema) {
        this.shardKeySchema = shardKeySchema;
    }

    public ShardView() {
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
