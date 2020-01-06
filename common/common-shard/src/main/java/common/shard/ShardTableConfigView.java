package common.shard;

import java.util.ArrayList;
import java.util.List;

public class ShardTableConfigView {
    public static List<String> notNeedShardSqlList = new ArrayList<>();
    public static List<String> shardTableByKeyDirectlyList = new ArrayList<>();
    public static List<String> shardTableByKeyDivideByList = new ArrayList<>();
    public static int divideByValue = 1;
    public static boolean isPrintShardSqlInfo;

    static {
        notNeedShardSqlList.add("SELECT LAST_INSERT_ID()");
    }
}
