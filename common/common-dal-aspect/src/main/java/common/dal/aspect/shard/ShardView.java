package common.dal.aspect.shard;

public class ShardView {
    private String shardKeySchema;
    private String shardKeyTable;
    private int shardKeyTableNumber;

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
