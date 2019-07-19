package Thread08;

import java.util.ArrayList;
import java.util.List;

public class ListThread {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->list.add(Thread.currentThread().getName())).start();
        }
        Thread.sleep(1000*1);  //很多线程还没有执行完就输出了结果
        System.out.println(list.size());
    }
}
