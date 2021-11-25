package homework2;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        final Vector<Integer> list = new Vector<>();
        System.out.println("list before : " + list);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService es = Executors.newFixedThreadPool(2);

        // 添加第一个元素
        es.execute(() -> {
            list.add(1);
            countDownLatch.countDown();
        });

        // 添加第二个元素
        es.execute(() -> {
            list.add(2);
            countDownLatch.countDown();
        });

        countDownLatch.await();
        System.out.println("list after : " + list);
        es.shutdown();
    }
}
