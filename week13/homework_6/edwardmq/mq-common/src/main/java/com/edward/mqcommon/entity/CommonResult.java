package com.edward.mqcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, "SUCCESS", data);
    }

    public static <T> CommonResult<T> fail(long code, String msg) {
        return new CommonResult<>(code, msg, null);
    }
}
