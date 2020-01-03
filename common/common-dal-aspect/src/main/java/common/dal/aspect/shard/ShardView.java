package common.dal.aspect.shard;

import lombok.Data;

@Data
public class ShardView {
    private String shardKeySchema;
    private String shardKeyTable;
    private int shardKeyTableNumber;

    public ShardView() {
        this.shardKeySchema = "";
        this.shardKeyTable = "";
        this.shardKeyTableNumber = -1;
    }
}
