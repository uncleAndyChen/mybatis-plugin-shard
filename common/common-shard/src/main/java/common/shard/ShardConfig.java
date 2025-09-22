package common.shard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各项参数说明，请参见：/biz/biz-config/src/main/resources/db-source.xml
 */
public class ShardConfig {
    public List<String> schemaKeyList;
    public Map<String, List<String>> shardSchemaInterfaceClassNameList;
    public List<String> shardTableDirectlyList;
    public Map<Integer, List<String>> shardTableDivideList;
    public boolean isPrintShardSqlInfo;
    public List<String> notNeedShardSqlList;

    public ShardConfig() {
        schemaKeyList = new ArrayList<>();
        shardTableDirectlyList = new ArrayList<>();
        shardTableDivideList = new HashMap<>();
        shardSchemaInterfaceClassNameList = new HashMap<>();
        isPrintShardSqlInfo = false;
        notNeedShardSqlList = new ArrayList<>();

        notNeedShardSqlList.add("SELECT LAST_INSERT_ID()");
    }

    public void setSchemaKeyList(List<String> schemaKeyList) {
        this.schemaKeyList = schemaKeyList;
    }

    public void setShardTableDirectlyList(List<String> shardTableDirectlyList) {
        this.shardTableDirectlyList = shardTableDirectlyList;
    }

    public void setShardTableDivideList(Map<Integer, List<String>> shardTableDivideList) {
        this.shardTableDivideList = shardTableDivideList;
    }

    public void setShardSchemaInterfaceClassNameList(Map<String, List<String>> shardSchemaInterfaceClassNameList) {
        this.shardSchemaInterfaceClassNameList = shardSchemaInterfaceClassNameList;
    }

    public void setPrintShardSqlInfo(boolean printShardSqlInfo) {
        isPrintShardSqlInfo = printShardSqlInfo;
    }

    public void setNotNeedShardSqlList(List<String> notNeedShardSqlList) {
        this.notNeedShardSqlList = notNeedShardSqlList;
    }
}
