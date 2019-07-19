package Thread09;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 使用并发容器
 * 内部实现了同步块
 */
public class CopyOnWriteList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(1000*1);  //很多线程还没有执行完就输出了结果
        System.out.println(list.size());
    }
}
