package Thread09;

/**
 * 使用同步方法模拟影院
 * Customer作为代理类代理影院类，因为同步方法只能在影院类中使用
 * 影院类需要获得代理类中的参数，可以通过Thread类强转，因为代理类继承了Thread类(Important!)
 */
public class CinemaThread03 {
    public static void main(String[] args) {
        ARCCinema amc = new ARCCinema(5, "AMC");
        new ARCCustomer(amc, 2, "aaa").start();
        new ARCCustomer(amc, 3, "bbb").start();
    }
}

class ARCCinema implements Runnable{
    private int available;
    private String name;

    public ARCCinema(int available, String name) {
        this.available = available;
        this.name = name;
    }


    @Override
    public void run() {
        ARCCustomer customer = (ARCCustomer)Thread.currentThread();
        boolean b = this.purchase(customer.getSeats());
        if (b) {
            System.out.println(customer.getName() + " 购票成功，购票" + customer.getSeats());
        } else {
            System.out.println(customer.getName() + " 购票失败，余票不足");
        }
    }

    public synchronized boolean purchase(int seats) {
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

class ARCCustomer extends Thread {
    private ARCCinema cinema;
    private int seats;
    private String name;

    public ARCCustomer(Runnable target, int seats, String name) {
        super(target, name);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }
}
