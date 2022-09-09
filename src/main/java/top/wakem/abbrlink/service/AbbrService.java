package top.wakem.abbrlink.service;


import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import top.wakem.abbrlink.common.constants.BizExcepConstants;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.exception.BizException;
import top.wakem.abbrlink.common.utils.UrlUtils;
import top.wakem.abbrlink.dao.entity.LinkAbbr;
import top.wakem.abbrlink.dao.mapper.LinkAbbrMapper;
import top.wakem.abbrlink.dao.repository.LinkAbbrRepository;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * date: 2022-08-29
 * author: Wakem
 */

@Service
public class AbbrService {

    private final static int UUID_BATCH_SIZE = 5;

    @Resource
    private LinkAbbrMapper linkAbbrMapper;

    @Resource
    private LinkAbbrRepository linkAbbrRepository;

    /**
     * 增加短链接
     * @param link 长连接
     * @param expireDays 有效天数
     */
    public String addLink(String link, Long expireDays) {
        if (!UrlUtils.isUrl(link)) {
            throw new BizException(BizExcepConstants.WRONG_PARAM, "链接校验失败");
        }
        if (Objects.nonNull(expireDays) && expireDays <= 0) {
            throw new BizException(BizExcepConstants.WRONG_PARAM, "有效天数校验失败");
        }
        String abbr = getAvailableLinkAbbr();
        LinkAbbr linkAbbr = new LinkAbbr();
        linkAbbr.setLinkAbbr(abbr);
        linkAbbr.setLink(link);
        linkAbbr.setCreateTime(new Date());
        linkAbbr.setExpireTime(new Date());
        int res = linkAbbrRepository.save(linkAbbr);

        if (res <= 0) {
            throw new BizException(BizExceptionEnum.SYSTEM_ERROR);
        }

        return abbr;
    }

    private String getAvailableLinkAbbr() {
        while (true) {
            String uuid = getUUID();
            if (!linkAbbrRepository.checkUUIDExist(uuid)) {
                return uuid;
            }
        }
    }

    private static String getUUID() {
        String uuid;
        uuid = UUID.randomUUID().toString();
        return  uuid.substring(uuid.length() - 7);
    }

    public String getLink(String abbr) {
        if (StringUtils.isNullOrEmpty(abbr)) {
            throw new BizException(BizExceptionEnum.PARAM_WRONG);
        }
        LinkAbbr linkAbbr = linkAbbrRepository.getByAbbr(abbr);
        if (Objects.isNull(linkAbbr)) {
            throw new BizException(BizExceptionEnum.PARAM_WRONG);
        }

        return linkAbbr.getLink();
    }
}
