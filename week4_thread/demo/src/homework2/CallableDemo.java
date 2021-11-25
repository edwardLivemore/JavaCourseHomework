package homework2;

import java.util.concurrent.Callable;

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        int num = 0;
        System.out.println("num before : " + num);
        Callable<Integer> callable = () -> 1;
        num = callable.call();
        System.out.println("num after : " + num);
    }
}
