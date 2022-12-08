package top.wakem.abbrlink.service;


import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import top.wakem.abbrlink.common.constants.BizExcepConstants;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.exception.BizException;
import top.wakem.abbrlink.common.response.BaseResponse;
import top.wakem.abbrlink.common.response.ExtraResponse;
import top.wakem.abbrlink.common.utils.QRCodeGenerator;
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
@Slf4j
public class AbbrService {

    private final static int UUID_BATCH_SIZE = 5;

    private final static int WIDTH = 300;

    @Resource
    private LinkAbbrMapper linkAbbrMapper;

    @Resource
    private LinkAbbrRepository linkAbbrRepository;

    /**
     * 增加短链接
     * @param link 长连接
     * @param expireDays 有效天数
     */
    public BaseResponse<String> addLink(String link, Integer expireDays) {
        link = UrlUtils.preHandleUrl(link);
        if (!UrlUtils.isUrl(link)) {
            throw new BizException(BizExcepConstants.WRONG_PARAM, "链接校验失败");
        }
        if (Objects.nonNull(expireDays) && expireDays <= 0) {
            throw new BizException(BizExcepConstants.WRONG_PARAM, "有效天数校验失败");
        }
        link = link.trim();
        String abbr = getAvailableLinkAbbr();
        LinkAbbr linkAbbr = new LinkAbbr();
        linkAbbr.setLinkAbbr(abbr);
        linkAbbr.setLink(link);
        Date now = new Date();
        linkAbbr.setCreateTime(now);
        linkAbbr.setExpireTime(DateUtils.addDays(now, expireDays));
        log.info("添加短链接: link: {}, abbr: {}", link, abbr);
        int res = linkAbbrRepository.save(linkAbbr);

        if (res <= 0) {
            throw new BizException(BizExceptionEnum.SYSTEM_ERROR);
        }

        return BaseResponse.success(abbr);
    }

    public BaseResponse<String> getQRCode(String content) {
        return BaseResponse.success(QRCodeGenerator.getQRCode2Base64(content, WIDTH, WIDTH));
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
