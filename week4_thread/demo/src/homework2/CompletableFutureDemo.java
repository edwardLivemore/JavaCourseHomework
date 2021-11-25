package homework2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int num = 0;
        System.out.println("num before : " + num);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync( () -> 1);
        num = completableFuture.get();
        System.out.println("num after : " + num);
    }
}
