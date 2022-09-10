package top.wakem.abbrlink.common.response;

import lombok.Data;
import top.wakem.abbrlink.common.enums.BizExceptionEnum;
import top.wakem.abbrlink.common.enums.ResponseCodeEnum;

@Data
public class BaseResponse<T> {
    private T data;
    private Integer code;

    public static <E> BaseResponse<E> success(E data) {
        BaseResponse<E> response = new BaseResponse<>();
        response.setData(data);
        response.setCode(ResponseCodeEnum.SUCCESS.getCode());
        return response;
    }

    public static BaseResponse<String> fail(BizExceptionEnum exceptionEnum) {
        BaseResponse<String> resp = new BaseResponse<>();
        resp.setCode(exceptionEnum.getCode());
        resp.setData(exceptionEnum.getDesc());
        return resp;
    }
}
