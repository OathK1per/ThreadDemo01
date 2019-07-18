package Thread04;

/**
 * 静态代理
 * 公共接口
 * 1.真实对象
 * 2.代理对象：与真实对象实现同一个公共接口，使用构造函数来代理真实对象，在该接口的实现方法中调用真实对象的实现方法
 */
public class StaticProxy {
    public static void main(String[] args) {
        new Cooker(new Vegetable()).taste();
    }
}

interface Cookable {
    void taste();
}

class Vegetable implements Cookable {

    @Override
    public void taste() {
        System.out.println("Delicious Vegetable!");
    }
}

class Cooker implements Cookable {
    private Cookable cookable;

    public Cooker(Cookable cookable) {
        this.cookable = cookable;
    }

    void prepare () {
        System.out.println("prepare food");
    }

    @Override
    public void taste() {
        prepare();
        this.cookable.taste();
        after();
    }

    void after () {
        System.out.println("wash dishes");
    }
}
