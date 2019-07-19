package Thread10;

/**
 * 形成死锁：存在多个共享资源时，各自拥有一部分资源，且需要对方手中的资源才能继续运行
 * 互相不释放资源从而相互等待
 *
 * 避免：不要在同一个代码块中，同时持有多个对象的锁
 */
public class DeadLock {
    public static void main(String[] args) {
        new Thread(new Play(0, "aaa")).start();
        new Thread(new Play(1, "bbb")).start();
    }
}

class Monitor {

}

class PS4 {

}

class Play implements Runnable {
    static PS4 ps4 = new PS4();
    static Monitor monitor = new Monitor();
    private int choice;
    private String person;

    public Play(int choice, String person) {
        this.choice = choice;
        this.person = person;
    }

    @Override
    public void run() {
//        play();
        play2();
    }

    /**
     * 造成死锁
     */
    private void play() {
        if (choice == 0) {
            synchronized (ps4) {
                System.out.println(person + "占有了PS4");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (monitor) {
                    System.out.println(person + "占有了屏幕");
                }
            }
        } else {
            synchronized (monitor) {
                System.out.println(person + "占有了屏幕");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ps4) {
                    System.out.println(person + "占有了PS4");
                }
            }
        }
    }
    /**
     * 解决死锁的办法：不要进行锁的嵌套，使用后就释放掉
     */
    private void play2() {
        if (choice == 0) {
            synchronized (ps4) {
                System.out.println(person + "占有了PS4");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (monitor) {
                System.out.println(person + "占有了屏幕");
            }
        } else {
            synchronized (monitor) {
                System.out.println(person + "占有了屏幕");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            synchronized (ps4) {
                System.out.println(person + "占有了PS4");
            }
        }
    }
}
