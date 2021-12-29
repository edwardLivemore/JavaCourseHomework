package io.kimmking.rpcfx.api;

import io.kimmking.rpcfx.demo.api.Service;
import lombok.Data;

@Data
public class RpcfxRequest<T extends Service> {
  private Class<T> serviceClass;
  private String method;
  private Object[] params;
}
