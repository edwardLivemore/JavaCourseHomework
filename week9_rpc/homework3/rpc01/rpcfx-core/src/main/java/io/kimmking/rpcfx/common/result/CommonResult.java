package io.kimmking.rpcfx.common.result;

import io.kimmking.rpcfx.common.exception.RpcfxException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;

    private String msg;

    private T data;

    public static CommonResult ok(Object data) {
        return new CommonResult(200, null, data);
    }

    public static CommonResult fail(RpcfxException e){
        return new CommonResult(e.getCode(), e.getMsg(), null);
    }
}
