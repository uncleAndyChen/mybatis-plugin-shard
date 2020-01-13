package common.shard;

import common.aspect.DataSourceAspect;
import common.aspect.HandleDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class ShardTablePolicy {
    private static Log log = LogFactory.getLog(ShardTablePolicy.class);

    public static String getRealExecuteSql(String originalSql, ShardRequest shardRequest) {
        String convertedSql = originalSql;
        String convertedTableName;
        String toReplaceTableName;

        // 针对直接添加后缀的分表策略
        // shardKeyTable 与 shardKeyTableNumber 二选一
        // shardKeyTable 的优先级高于 shardKeyTableNumber
        for (String tableName : DataSourceAspect.shardConfig.shardTableDirectlyList) {
            toReplaceTableName = "`" + tableName + "`";

            if (!originalSql.contains(toReplaceTableName)) {
                continue;
            }

            if (shardRequest.getShardKeyTable() != null && shardRequest.getShardKeyTable().length() > 0) {
                // 直接指定后缀的分表策略
                convertedTableName = "`" + tableName + "_" + shardRequest.getShardKeyTable() + "`";
                convertedSql = convertedSql.replaceAll(toReplaceTableName, convertedTableName);
            } else if (shardRequest.getShardKeyTableNumber() > 0) {
                // 基于用户分表编号作为分表后缀的分表策略
                convertedTableName = "`" + tableName + "_" + shardRequest.getShardKeyTableNumber() + "`";
                convertedSql = convertedSql.replaceAll(toReplaceTableName, convertedTableName);
            }
        }

        if (shardRequest.getShardKeyTableNumber() <= 0) {
            ifNeedThanPrintSql(originalSql, convertedSql);
            return convertedSql;
        }

        // 基于用户分表编号除以一个数，余数作为分表后缀的分表策略
        for (Map.Entry<Integer, List<String>> entry : DataSourceAspect.shardConfig.shardTableDivideList.entrySet()) {
            int divideValue = entry.getKey();
            List<String> shardTableDivideList = entry.getValue();

            if (divideValue <= 0 || shardTableDivideList.size() == 0) {
                continue;
            }

            for (String tableName : shardTableDivideList) {
                toReplaceTableName = "`" + tableName + "`";

                if (!originalSql.contains(toReplaceTableName)) {
                    continue;
                }

                convertedTableName = "`" + tableName + "_" + shardRequest.getShardKeyTableNumber() % divideValue + "`";
                convertedSql = convertedSql.replaceAll(toReplaceTableName, convertedTableName);
            }
        }

        ifNeedThanPrintSql(originalSql, convertedSql);
        return convertedSql;
    }

    private static void ifNeedThanPrintSql(String originalSql, String convertedSql) {
        if (DataSourceAspect.shardConfig.isPrintShardSqlInfo) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n--------------shard table sql start-------------- \n")
                    .append("current data source key：")
                    .append(HandleDataSource.getSchemaKey())
                    .append("\nbefore shard table sql：\n--->\n")
                    .append(originalSql)
                    .append("\n<---\n")
                    .append("after shard table sql：\n--->\n")
                    .append(convertedSql)
                    .append("\n<---")
                    .append("\n--------------shard table sql end  --------------\n");

            log.info(sb.toString());
        }
    }
}
