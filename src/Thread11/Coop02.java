package Thread11;

/**
 * 协作模型：信号灯法
 * 使用flag作为切换的标志
 */
public class Coop02 {
    public static void main(String[] args) {
        Computer pc = new Computer();
        new Dancer(pc).start();
        new Viewer(pc).start();
    }
}
// 表演者
class Dancer extends Thread {
    private Computer pc;

    public Dancer(Computer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                pc.record("NBS");
            } else {
                pc.record("CBN");
            }
        }
    }
}
// 观众
class Viewer extends Thread {
    private Computer pc;

    public Viewer(Computer pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            pc.play();
        }
    }
}
// 电脑
class Computer {
    String voice;
    boolean flag = true;

    public synchronized void record(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.voice = voice;
        flag = !flag;
        System.out.println("表演了 " + voice);
        this.notifyAll();
    }

    public synchronized String play() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = !flag;
        System.out.println("观看了 " + voice);
        this.notifyAll();
        return voice;
    }
}