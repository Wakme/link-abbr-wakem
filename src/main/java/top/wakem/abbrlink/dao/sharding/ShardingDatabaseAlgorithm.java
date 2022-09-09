package top.wakem.abbrlink.dao.sharding;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShardingDatabaseAlgorithm implements StandardShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        int res = 0;
        char[] chars = preciseShardingValue.getValue().toCharArray();
        for (int i = 3; i < chars.length; i ++ ) {
            res = (res + chars[i]) % 2;
        }
        return "m" + (res + 1);
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
        return "m";
    }

}
