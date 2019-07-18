package Thread06;

import java.util.Scanner;

/**
 * 停止线程的两种方式：
 * 1. 根据次数自然停止
 * 2. 通过设置flag强行停止
 */
public class StopCondition implements Runnable {
    private int i;
    private static boolean flag = true;

    public StopCondition(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (i > 0) {
            System.out.println("通过次数自然停止-->");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
    }

    public static void main(String[] args) {
        new Thread(new StopCondition(10)).start();

        new Thread(() -> {
            while (flag) {
                System.out.println("通过flag强行停止-->");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /**
         * 停止条件：输入任意字符
         */
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            flag = false;
        }
    }
}
