package top.wakem.abbrlink.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;

@Data
@AllArgsConstructor
public class BizException extends RuntimeException{
    private Integer code;
    private String message;

    public BizException(){}

    public BizException(BizExceptionEnum e) {
        final BizException bizException = new BizException();
        bizException.setCode(e.getCode());
        bizException.setMessage(e.getDesc());
    }

}
