package Thread08;

/**
 * 线程安全
 * synchronized 锁的是对象的资源
 * 同步方法或者同步块
 */
public class SynBlockTicket implements Runnable {

    private int ticketNum;
    private boolean flag = true;

    public SynBlockTicket(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    @Override
    public void run() {
        while (flag) {
            syn();
        }
    }

    private void syn() {
        /**
         * double check 使代码运行更有效率，而不会出现都在等待进锁的时候再退出出来
         */
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        // 同步里面的参数需是地址不会改变的量，传ticketNum这种进来无效
        synchronized (this) {
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
    }

    public static void main(String[] args) {
        SynBlockTicket web = new SynBlockTicket(100);

        new Thread(web, "ccc").start();
        new Thread(web, "aaa").start();
        new Thread(web, "bbb").start();

    }
}
