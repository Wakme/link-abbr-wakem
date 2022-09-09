package top.wakem.abbrlink.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import top.wakem.abbrlink.dao.entity.LinkAbbr;
import top.wakem.abbrlink.dao.mapper.LinkAbbrMapper;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class LinkAbbrRepository {

    public static final Integer BATCH_SIZE = 100;

    public static final String LINK_ABBR_COLUMN_NAME = "link_abbr";

    @Resource
    LinkAbbrMapper linkAbbrMapper;

    public List<LinkAbbr> batchGetLinkAbbrByUUIDs(List<String> uuids) {
        if (CollectionUtils.isEmpty(uuids)) {
            return Lists.newArrayList();
        }
        List<List<String>> partitions = Lists.partition(uuids, BATCH_SIZE);

        List<LinkAbbr> res = new ArrayList<>();
        for (List<String> subList : partitions) {
            QueryWrapper<LinkAbbr> wrapper = new QueryWrapper<>();
            wrapper.in(LINK_ABBR_COLUMN_NAME, subList);
            List<LinkAbbr> linkAbbrs = linkAbbrMapper.selectList(wrapper);
            if (!CollectionUtils.isEmpty(linkAbbrs)) {
                res.addAll(linkAbbrs);
            }
        }
        return res;
    }

    public boolean checkUUIDExist(String uuid) {
        if (StringUtils.isBlank(uuid)) return false;
        QueryWrapper<LinkAbbr> wrapper = new QueryWrapper<>();
        wrapper.eq(LINK_ABBR_COLUMN_NAME, uuid);
        return !linkAbbrMapper.selectList(wrapper).isEmpty();
    }

    public int save(LinkAbbr linkAbbr) {
        if (Objects.isNull(linkAbbr)) {
            return 0;
        }
        return linkAbbrMapper.insert(linkAbbr);
    }

    public LinkAbbr getByAbbr(String abbr) {
        if (StringUtils.isBlank(abbr)) {
            return null;
        }
        QueryWrapper<LinkAbbr> wrapper = new QueryWrapper<>();
        wrapper.eq(LINK_ABBR_COLUMN_NAME, abbr);
        List<LinkAbbr> linkAbbrs = linkAbbrMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(linkAbbrs)) {
            return null;
        }
        return linkAbbrs.stream().findFirst().orElse(null);
    }
}
