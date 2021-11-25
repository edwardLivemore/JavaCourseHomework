package homework2;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger num = new AtomicInteger();
        System.out.println("list before : " + num.get());
        Thread thread = new Thread(num::getAndIncrement);
        thread.start();
        thread.join();
        System.out.println("list after : " + num.get());
    }
}
