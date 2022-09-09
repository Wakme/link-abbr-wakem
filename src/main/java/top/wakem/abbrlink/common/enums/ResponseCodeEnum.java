package top.wakem.abbrlink.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    SUCCESS(1, "成功"),
    FAIL(2, "失败")
    ;

    private Integer code;
    private String desc;
}
