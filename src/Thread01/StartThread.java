package Thread01;

/**
 * 继承Thread类
 */
public class StartThread extends Thread {

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            System.out.println("里世界-->" + j);
        }
    }

    public static void main(String[] args) {
        StartThread st = new StartThread();
        st.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("表世界-->" + i);
        }
    }
}
