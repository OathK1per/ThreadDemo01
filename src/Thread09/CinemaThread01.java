package Thread09;

/**
 * 模拟影院抢票系统 初始版
 */
public class CinemaThread01 {
    public static void main(String[] args) {
        Cinema amc = new Cinema(5, "AMC");
        Customer aaa = new Customer(amc, 2, "aaa");
        Customer bbb = new Customer(amc, 4, "bbb");

        new Thread(aaa).start();
        new Thread(bbb).start();
    }
}

class Cinema {
    private int available;
    private String name;

    public Cinema(int available, String name) {
        this.available = available;
        this.name = name;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    /**
     * 购买票系统应该放在影院系统里面而不应该在顾客系统里面
     * @param seats
     * @return
     */
    public boolean purchase(int seats) {
        if (seats > available) {
            return false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        available -= seats;
        return true;
    }
}

class Customer implements Runnable {
    private Cinema cinema;
    private int seats;
    private String name;

    public Customer(Cinema cinema, int seats, String name) {
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
                System.out.println(name + " 购票成功，余票" + cinema.getAvailable());
            } else {
                System.out.println(name + " 购票失败，余票不足");
            }
        }
    }

}
