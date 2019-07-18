package Thread03;

/**
 * 同等分资源，分别拿，可以使用flag来决定结束循环的条件
 */
public class Racer implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (Thread.currentThread().getName().equals("rabbit") && i % 30 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }

    public static void main(String[] args) {
        Racer racer1 = new Racer();
        Racer racer2 = new Racer();

        new Thread(racer1, "rabbit").start();
        new Thread(racer2, "tortoise").start();
    }
}
