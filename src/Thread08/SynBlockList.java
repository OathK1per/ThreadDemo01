package Thread08;

import java.util.ArrayList;
import java.util.List;
/**
 * 容器问题
 * 使用同步块，目标更明确，放入的是具体的需要锁的对象
 */
public class SynBlockList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(1000*1);  //很多线程还没有执行完就输出了结果
        System.out.println(list.size());
    }
}
