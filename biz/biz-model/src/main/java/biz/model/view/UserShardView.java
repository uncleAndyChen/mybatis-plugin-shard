package biz.model.view;

import lombok.Data;

@Data
public class UserShardView {
    private int shardKeyTableNumber;
    private String shardKeySchema;
}
