package Thread07;

/**
 * 线程分为守护线程和用户线程，默认创建的线程都是用户线程
 * CPU不会等待守护线程执行完毕
 */
public class DaemonThread {
    public static void main(String[] args) {
        You you = new You();
        God god = new God();
        god.setDaemon(true);
        god.start();
        you.start();
    }
}

class You extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Enjoy Life......");
        }
        System.out.println("Game Over!");
    }
}

class God extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you");
        }
    }
}
