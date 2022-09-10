package top.wakem.abbrlink.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.exception.BizException;
import top.wakem.abbrlink.common.response.BaseResponse;

@Controller
@Slf4j
public class ExceptionHandler {

    public BaseResponse<String> bizExceptionHandler(BizException e) {
        log.error("业务异常", e);
        return BaseResponse.fail(BizExceptionEnum.SYSTEM_ERROR);
    }

}