package Thread01;

/**
 * 实现Runnable接口
 */
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("里世界-->" + j);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunnableThread rt = new RunnableThread();
        Thread t = new Thread(rt);
        t.start();

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1);
            System.out.println("表世界-->" + i);
        }
    }
}
