package Thread07;

/**
 * 优先级：从1到10，10最高
 * 默认优先级为5
 * 优先级不代表绝对顺序，仅仅是相对概率
 */
public class PriorityThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
        Thread.yield();
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());

        Thread t1 = new Thread(new PriorityThread(), "Thread 1");
        Thread t2 = new Thread(new PriorityThread(), "Thread 2");
        Thread t3 = new Thread(new PriorityThread(), "Thread 3");
        Thread t4 = new Thread(new PriorityThread(), "Thread 4");
        Thread t5 = new Thread(new PriorityThread(), "Thread 5");
        Thread t6 = new Thread(new PriorityThread(), "Thread 6");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);
        t4.setPriority(Thread.MIN_PRIORITY);
        t5.setPriority(Thread.MIN_PRIORITY);
        t6.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
