package top.wakem.abbrlink.common.response;

import lombok.Data;
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
}
