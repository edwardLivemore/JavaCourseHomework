package homework2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int num = 0;
        System.out.println("num before : " + num);

        // 直接run
        FutureTask<Integer> futureTask1 = new FutureTask(() -> 1);
        futureTask1.run();
        num = futureTask1.get();
        System.out.println("num after : " + num);

        // 通过线程池
        FutureTask<Integer> futureTask2 = new FutureTask(() -> 2);
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(futureTask2);
        num = futureTask2.get();
        System.out.println("num after : " + num);
        es.shutdown();
    }
}
