package io.kimmking.rpcfx.common.handler;

import io.kimmking.rpcfx.common.exception.RpcfxException;
import io.kimmking.rpcfx.common.result.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RpcfxExceptionHanlderAdvicer {

    @ExceptionHandler(value = RpcfxException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult catchException(RpcfxException e){
        return CommonResult.fail(e);
    }
}
