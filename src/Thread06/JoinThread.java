package Thread06;

/**
 * join：插队线程，能够直接插队进来，直到线程结束
 * 与sleep和yield是静态方法不同，join需要对象实现
 */
public class JoinThread {
    public static void main(String[] args) {
        System.out.println("Story Begin: ");
        new Father().start();
    }
}

class Father extends Thread {

    @Override
    public void run() {
        System.out.println("I wanna cook");
        System.out.println("But I can't walk away because of my leg");
        System.out.println("I ask my son to buy some vegetable and meat in supermarket");
        System.out.println("I give my son some cash");
        Son son = new Son();
        son.start();
        try {
            son.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("My son is lost");
        }
        System.out.println("I get these things and begin cooking");
    }
}

class Son extends Thread {

    @Override
    public void run() {
        System.out.println("I go to supermarket");
        System.out.println("I meet a friend and we talk a lot");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("My friend interrupt me and leave here");
            }
            System.out.println((i + 1) + " second left");
        }
        System.out.println("I continue consuming");
        System.out.println("I walk back");
    }
}
