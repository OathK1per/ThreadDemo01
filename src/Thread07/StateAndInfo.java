package Thread07;

/**
 * getState(): 观察线程的状态：NEW, RUNNABLE, BLOCK, TIMED-WAITING, WAITING, TERMINATED
 * activeCount(): 观察线程的运作数
 * isAlive(); 判断线程是否存活
 * setName(), getName(): 更改名字
 * currentThread(): 当前线程对象
 */
public class StateAndInfo implements Runnable {
    @Override
    public void run() {
        for (int j = 0; j < 3; j++) {
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StateAndInfo so = new StateAndInfo();
        Thread t = new Thread(so);

        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());

        System.out.println(t.isAlive());

        while(true) {
            if (Thread.activeCount() == 2) {
                break;
            }
            System.out.println(t.getState());
            Thread.sleep(1000);
        }
        System.out.println(t.getState());

        System.out.println(t.isAlive());
    }
}
