package Thread11;

/**
 *
 * 协作模型：生产者消费者模型：管程法
 * 通过使用容器在中间做缓冲使用
 * */
public class Coop01 {
    public static void main(String[] args) {

        Cache cache = new Cache();
        new Productor(cache).start();
        new Consumer(cache).start();
    }
}

//生产者
class Productor extends Thread {

    private Cache cache;
    private int count = 0;

    public Productor(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            cache.push(new Products(count));
            System.out.println("生产了产品-->" + count);
            count++;
            if (count == 100) {
                break;
            }
        }
    }
}
//消费者
class Consumer extends Thread {

    private Cache cache;
    private int num = 0;

    public Consumer(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            Products products = cache.pop();
            System.out.println("消费了产品-->" + products.count);
            num++;
            if (num == 100) {
                break;
            }
        }
    }
}
//缓冲区
class Cache {
    Products[] productLine = new Products[10];
    int size = 0;

    /**
     * 生产 放入 唤醒和等待的条件
     * @param products
     */
    public synchronized void push(Products products) {
        if (size == productLine.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productLine[size++] = products;
        this.notifyAll();
    }

    /**
     * 消费 获取 等待和唤醒的条件
     * @return
     */
    public synchronized Products pop() {
        if (size == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        return productLine[--size];
    }
}
//生产物品
class Products {
    int count;

    public Products(int count) {
        this.count = count;
    }
}