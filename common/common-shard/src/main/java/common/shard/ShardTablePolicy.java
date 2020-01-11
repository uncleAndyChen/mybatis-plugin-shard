package common.shard;

import common.aspect.HandleDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ShardTablePolicy {
    private static Log log = LogFactory.getLog(ShardTablePolicy.class);

    public static String getRealExecuteSql(String originalSql, ShardRequest shardRequest) {
        String convertedSql = originalSql;
        String convertedTableName;

        // 针对直接添加后缀的表
        // 基于编号分表的策略优先
        if (shardRequest.getShardKeyTableNumber() > 0) {
            for (String tableName : ShardTableConfigView.shardTableByKeyDirectlyList) {
                convertedTableName = "`" + tableName + "_" + shardRequest.getShardKeyTableNumber() + "`";
                convertedSql = convertedSql.replaceAll("`" + tableName + "`", convertedTableName);
            }
        } else if (shardRequest.getShardKeyTable() != null && shardRequest.getShardKeyTable().length() > 0) {
            for (String tableName : ShardTableConfigView.shardTableByKeyDirectlyList) {
                convertedTableName = "`" + tableName + "_" + shardRequest.getShardKeyTable() + "`";
                convertedSql = convertedSql.replaceAll("`" + tableName + "`", convertedTableName);
            }
        }

        // 针对需要除一个数得到后缀的表
        if (shardRequest.getShardKeyTableNumber() > 0 && ShardTableConfigView.divideByValue > 1) {
            for (String tableName : ShardTableConfigView.shardTableByKeyDivideByList) {
                convertedTableName = "`" + tableName + "_" + getByKeyDivideBy(shardRequest.getShardKeyTableNumber()) + "`";
                convertedSql = convertedSql.replaceAll("`" + tableName + "`", convertedTableName);
            }
        }

        if (ShardTableConfigView.isPrintShardSqlInfo) {
            StringBuffer sb = new StringBuffer();

            sb
                    .append("\n--------------shard table sql start-------------- \n")
                    .append("current data source key：")
                    .append(HandleDataSource.getSchemaKey())
                    .append("\nbefore shard table  sql：\n--->\n")
                    .append(originalSql)
                    .append("\n<---\n")
                    .append("after shard table  sql：\n--->\n")
                    .append(convertedSql)
                    .append("\n<---")
                    .append("\n--------------shard table sql end  --------------\n");

            log.info(sb.toString());
        }

        return convertedSql;
    }

    private static int getByKeyDivideBy(int shardNumber) {
        return new Double(Math.ceil(shardNumber / ShardTableConfigView.divideByValue)).intValue();
    }
}

