package top.wakem.abbrlink.common.response;

import lombok.Data;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.enums.ResponseCodeEnum;

@Data
public class ExtraResponse<T, R> extends BaseResponse<T> {
    private R extra;
    private Integer code;

    public static <E, R> ExtraResponse<E, R> success(E data, R extra) {
        ExtraResponse<E, R> response = new ExtraResponse<>();
        response.setData(data);
        response.setExtra(extra);
        response.setCode(ResponseCodeEnum.SUCCESS.getCode());
        return response;
    }

    public static BaseResponse<String> fail(BizExceptionEnum exceptionEnum) {
        BaseResponse<String> resp = new BaseResponse<>();
        resp.setCode(exceptionEnum.getCode());
        resp.setData(exceptionEnum.getDesc());
        return resp;
    }

    public static BaseResponse<String> fail(String message) {
        BaseResponse<String> resp = new BaseResponse<>();
        resp.setCode(ResponseCodeEnum.FAIL.getCode());
        resp.setData(message);
        return resp;
    }
}