package top.wakem.abbrlink.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BizExceptionEnum {
    PARAM_WRONG(100, "参数异常"),
    SYSTEM_ERROR(101, "系统异常")
    ;

    private Integer code;
    private String desc;
}
