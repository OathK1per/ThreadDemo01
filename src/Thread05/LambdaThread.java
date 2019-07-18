package Thread05;

public class LambdaThread {

    /**
     * 静态内部类的实现
     */
    static class StaticNested implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("静态内部类-->" + i);
            }
        }
    }

    public static void main(String[] args) {

        /**
         * 局部内部类的实现
         */
        class LocalNested implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("局部内部类-->" + i);
                }
            }
        }

        new Thread(new StaticNested()).start();
        new Thread(new LocalNested()).start();
        /**
         * 匿名内部类，必须借助接口或者父类
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("匿名内部类-->" + i);
                }
            }
        }).start();
        /**
         * Lambda表达式
         */
        new Thread(()-> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Lambda表达式-->" + i);
            }
        }).start();
    }
}
