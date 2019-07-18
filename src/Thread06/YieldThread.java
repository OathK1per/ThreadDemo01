package Thread06;

/**
 * yield会释放手上资源，礼让线程，直接进入就绪状态，不经过阻塞状态
 * 不一定会让成功
 */
public class YieldThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Run方法正在运行-->" + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new YieldThread()).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Main方法正在运行-->" + i);
            if (i%10 == 0) {
                Thread.yield();
            }
        }
    }
}
