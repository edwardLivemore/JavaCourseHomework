package homework2;

import java.time.Period;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        Vector<Integer> list = new Vector<>();
        System.out.println("list before : " + list);

        ExecutorService es1 = Executors.newSingleThreadExecutor();
        ExecutorService es2 = Executors.newFixedThreadPool(2);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,
                () -> es1.execute(() -> {
                    System.out.println("list after : " + list);
                    es1.shutdown();
                }));

        es2.execute(() -> {
            list.add(1);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        es2.execute(() -> {
            list.add(2);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        es2.shutdown();
    }
}
