package Thread08;

/**
 * 取钱问题
 * 以this为对象，进行synchronized操作，不能阻止问题出现
 */
public class DrawThread {
    public static void main(String[] args) {
        Account account = new Account(100, "aaa");
        DrawMoney you = new DrawMoney(account, 80, "you");
        DrawMoney other = new DrawMoney(account, 60, "other");

        you.start();
        other.start();
    }
}

class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class DrawMoney extends Thread {

    Account account;
    int pocketMoney;
    int DrawingMoney;

    public DrawMoney(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        DrawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    private synchronized void test() {
        if (account.money - DrawingMoney < 0) {
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pocketMoney += DrawingMoney;
        account.money -= DrawingMoney;
        System.out.println("口袋里的钱-->" + pocketMoney);
        System.out.println("账户中的钱-->" + account.money);
    }
}