package Thread08;

/**
 * 线程安全
 * synchronized 锁的是对象的资源
 * 同步方法或者同步块
 */
public class Syn01 implements Runnable {

    private int ticketNum;
    private boolean flag = true;

    public Syn01(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    @Override
    public void run() {
        while (flag) {
            syn();
        }
    }

    private synchronized void syn() {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->" + ticketNum--);
    }

    public static void main(String[] args) {
        Syn01 web = new Syn01(100);

        new Thread(web, "ccc").start();
        new Thread(web, "aaa").start();
        new Thread(web, "bbb").start();

    }
}
