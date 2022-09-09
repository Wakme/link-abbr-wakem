package top.wakem.abbrlink.dao.sharding;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

public class ShardingTableAlgorithm implements StandardShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        int res = 0;
        char[] chars = preciseShardingValue.getValue().toCharArray();
        for (int i = 0; i < chars.length - 3; i ++ ) {
            char c = chars[i];
            res = (res + c) % 2;
        }
        return "link_abbr_0" + (res + 1);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "link_abbr";
    }
}
