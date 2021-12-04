package handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class CustomInvocationHandler implements InvocationHandler {
    private final Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start at :" + LocalDateTime.now());
        Object invoke = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println("finish at :" + LocalDateTime.now());
        System.out.println("execute time : " + (end - start) + "ms");
        return invoke;
    }
}
