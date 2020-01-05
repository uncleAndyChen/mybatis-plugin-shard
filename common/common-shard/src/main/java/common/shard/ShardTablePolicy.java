package common.shard;

import common.aspect.HandleDataSource;
import common.lib.ConfigProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShardTablePolicy {
    public static List<String> notNeedShardSqlList;
    private static List<String> shardTableByKeyDirectlyList;
    private static List<String> shardTableByKeyDivideByList;
    private static double divideByValue;
    private static Log log = LogFactory.getLog(ShardTablePolicy.class);

    static {
        notNeedShardSqlList = new ArrayList<>();
        shardTableByKeyDirectlyList = new ArrayList<>();
        shardTableByKeyDivideByList = new ArrayList<>();
        divideByValue = 1;

        String shardTableByKeyDirectlyTables = ConfigProperties.getValue("shardTableByKeyDirectlyTables");
        String shardTableByKeyDivideByTables = ConfigProperties.getValue("shardTableByKeyDivideByTables");
        String divideByValueConfig = ConfigProperties.getValue("divideByValue");

        setListByStringSource(shardTableByKeyDirectlyList, shardTableByKeyDirectlyTables);
        setListByStringSource(shardTableByKeyDivideByList, shardTableByKeyDivideByTables);

        if (divideByValueConfig.length() > 0) {
            divideByValue = new Double(divideByValueConfig);
        }

        notNeedShardSqlList.add("SELECT LAST_INSERT_ID()");
    }

    public static String getRealExecuteSql(String originalSql, ShardView shardView) {
        String convertedSql = originalSql;
        String convertedTableName;

        // 针对直接添加后缀的表
        // 基于编号分表的策略优先
        if (shardView.getShardKeyTableNumber() > 0) {
            for (String tableName : shardTableByKeyDirectlyList) {
                convertedTableName = "`" + tableName + "_" + shardView.getShardKeyTableNumber() + "`";
                convertedSql = convertedSql.replaceAll("`" + tableName + "`", convertedTableName);
            }
        } else if (shardView.getShardKeyTable() != null && shardView.getShardKeyTable().length() > 0) {
            for (String tableName : shardTableByKeyDirectlyList) {
                convertedTableName = "`" + tableName + "_" + shardView.getShardKeyTable() + "`";
                convertedSql = convertedSql.replaceAll("`" + tableName + "`", convertedTableName);
            }
        }

        // 针对需要除一个数得到后缀的表
        if (shardView.getShardKeyTableNumber() > 0 && divideByValue > 1) {
            for (String tableName : shardTableByKeyDivideByList) {
                convertedTableName = "`" + tableName + "_" + getByKeyDivideBy(shardView.getShardKeyTableNumber()) + "`";
                convertedSql = convertedSql.replaceAll("`" + tableName + "`", convertedTableName);
            }
        }

        if (ConfigProperties.getValue("isPrintShardSqlInfo").equals("Y")) {
            log.info("current data source key：" + HandleDataSource.getSchemaKey());
            log.info("before shard table  sql：" + originalSql.replaceAll("\n", ""));
            log.info("after  shard table  sql：" + convertedSql.replaceAll("\n", ""));
        }

        return convertedSql;
    }

    private static int getByKeyDivideBy(int shardNumber) {
        return new Double(Math.ceil(shardNumber / divideByValue)).intValue();
    }

    private static void setListByStringSource(List<String> targetList, String sourceKeys) {
        if (sourceKeys.length() == 0) {
            return;
        }

        String[] keyArray = sourceKeys.split(",");
        targetList.addAll(Arrays.asList(keyArray));
    }
}

