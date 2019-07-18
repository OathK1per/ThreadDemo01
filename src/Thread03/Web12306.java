package Thread03;

/**
 * 一份资源，多个人来拿
 */
public class Web12306 implements Runnable {

    private int ticketNum = 100;

    @Override
    public void run() {
        int count = 0;
        while (ticketNum > 0) {
            /**
             * 模拟网络延时
             */
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketNum--;
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNum);
            count++;

        }
        System.out.println("name: " + Thread.currentThread().getName() + "-->" + count);
    }

    public static void main(String[] args) {
        Web12306 web = new Web12306();

        new Thread(web, "aaa").start();
        new Thread(web, "bbb").start();
        new Thread(web, "ccc").start();
    }
}
