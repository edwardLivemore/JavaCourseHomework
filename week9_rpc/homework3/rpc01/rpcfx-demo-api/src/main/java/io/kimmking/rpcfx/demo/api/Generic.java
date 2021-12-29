package io.kimmking.rpcfx.demo.api;

import lombok.Data;

@Data
public class Generic<T extends Service> {
    private T service;
}
