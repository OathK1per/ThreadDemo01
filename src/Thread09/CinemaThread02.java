package Thread09;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用同步块模拟影院
 * 影院类的参数作为需要锁住的内容，需要在同步块中加入其对象
 */
public class CinemaThread02 {
    public static void main(String[] args) {
        List<Integer> available = new ArrayList<>();
        available.add(1);
        available.add(2);
        available.add(4);
        available.add(5);
        available.add(9);
        AMCCinema amc = new AMCCinema(available, "AMC");

        List<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(2);

        List<Integer> bb = new ArrayList<>();
        bb.add(1);
        bb.add(4);
        bb.add(5);
        bb.add(9);
        AMCCustomer aaa = new AMCCustomer(amc, aa, "aaa");
        AMCCustomer bbb = new AMCCustomer(amc, bb, "bbb");

        new Thread(aaa).start();
        new Thread(bbb).start();
    }
}

class AMCCinema {
    private List<Integer> available;
    private String name;

    public AMCCinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    /**
     * 购买票系统应该放在影院系统里面而不应该在顾客系统里面
     * @param seats
     * @return
     */
    public boolean purchase(List<Integer> seats) {
        System.out.println("余票为" + available.toString());
        List<Integer> copy = new ArrayList<>();
        copy.addAll(available);
        copy.removeAll(seats);
        if (copy.size() + seats.size() != available.size()) {
            return false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        available = copy;
        return true;
    }
}

class AMCCustomer implements Runnable {
    private AMCCinema cinema;
    private List<Integer> seats;
    private String name;

    public AMCCustomer(AMCCinema cinema, List<Integer> seats, String name) {
        this.cinema = cinema;
        this.seats = seats;
        this.name = name;
    }

    @Override
    public void run() {
        boolean b;
        synchronized (cinema) {
            b = cinema.purchase(seats);
            if (b) {
                System.out.println(name + " 购票成功，位置为" + seats.toString());
            } else {
                System.out.println(name + " 购票失败，余票不足");
            }
        }
    }

}
