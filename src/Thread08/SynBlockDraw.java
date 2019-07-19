package Thread08;

/**
 * 取钱问题
 * 使用同步块，目标更明确，放入的是具体的需要锁的对象
 */
public class SynBlockDraw {
    public static void main(String[] args) {
        Account account = new Account(100, "aaa");
        SynDrawMoney you = new SynDrawMoney(account, 80, "you");
        SynDrawMoney other = new SynDrawMoney(account, 60, "other");

        you.start();
        other.start();
    }
}

class SynDrawMoney extends Thread {

    Account account;
    int pocketMoney;
    int DrawingMoney;

    public SynDrawMoney(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        DrawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    private void test() {
        if (account.money < 0) {
            return;
        }
        synchronized (account) {
            if (account.money - DrawingMoney < 0) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= DrawingMoney;
            System.out.println("账户中的钱-->" + account.money);
            pocketMoney += DrawingMoney;
            System.out.println("口袋里的钱-->" + pocketMoney);
        }
    }
}