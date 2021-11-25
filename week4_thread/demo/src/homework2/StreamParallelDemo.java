package homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamParallelDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            list.add(random.nextInt(100));
        }
        System.out.println("list before : " + list);

        list = list.stream().parallel().sorted().collect(Collectors.toList());
        System.out.println("list after : " + list);
    }
}
