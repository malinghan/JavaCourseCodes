package java0.conc0301.op;

public class WaitAndNotify {
    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        //生产者
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");
        //消费者
        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t2");
        //消费者
        Thread t3 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
        
    }
}

class MethodClass {
    // 定义生产最大量
    private final int MAX_COUNT = 20;
    
    int productCount = 0;

    //product,customer 锁的是同一个objct::methodClass
    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println("货舱已满,,.不必再生产");
                //如果满了，就阻塞当前线程,线程处于Waiting状态,不继续执行后面的代码

                wait();
            }else {
                productCount++;
            }
            //唤醒所有的消费者,t2或者t3抢到了锁，然后执行，其他线程必须阻塞wait
            notifyAll();
        }
    }
    
    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount <= 0) {
                System.out.println("货舱已无货...无法消费");
                //如果count为0,则wait
                wait();
            }else {
                productCount--;
            }
            
            notifyAll();
        }
    }
}


/**
 *
 *  //t1 释放了自己的锁，将自己的线程状态置为wait,t2,t3处于锁等待的状态，则抢占锁
 *  然后t2的customer方法抢到了锁,不断消费，且会一直notify其他线程,直到为0的时候
 *  t2释放锁，再挂起，然后有可能 t2或t1抢到锁，如果是t1抢到则会开始生产，如果是t3抢到则会提示无货
 *
 */