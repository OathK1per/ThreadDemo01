package Thread06;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep可以模拟网络延时(Web12306)，可以模拟倒计时
 * sleep不会释放手里的资源
 */
public class SleepThread {

    public static void main(String[] args) throws InterruptedException {
        long time = new Date().getTime();
        Date date = new Date(time);
        while (true) {
            System.out.println(new SimpleDateFormat("hh:mm:ss").format(date));
            Thread.sleep(1000);
            date = new Date(date.getTime() - 1000);
            if (time - 1000 * 60 * 10 > date.getTime()) {
                break;
            }
        }
    }
}
