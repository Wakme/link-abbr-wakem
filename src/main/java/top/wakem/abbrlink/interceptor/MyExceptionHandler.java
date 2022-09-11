package top.wakem.abbrlink.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.exception.BizException;
import top.wakem.abbrlink.common.response.BaseResponse;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public BaseResponse<String> bizExceptionHandler(BizException e) {
        log.error("业务异常", e);
        return BaseResponse.fail(e.getMessage());
    }

}
