package com.likang.myutil.util.thread;
/**
 * 资源
 */
public class Resource {
    /*资源序号*/
    private int number = 0;
    /*资源标记*/
    private boolean flag = false;

    /**
     * 生产资源
     */
    public synchronized void create() {
        while (flag) {//先判断标记是否已经生产了，如果已经生产，等待消费；
            try {
                wait();//让生产线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;//生产一个
        System.out.println(Thread.currentThread().getName() + "生产者------------" + number);
        flag = true;//将资源标记为已经生产
        notifyAll();//唤醒在等待操作资源的线程（队列）
    }

    /**
     * 消费资源
     */
    public synchronized void destroy() {
    	while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "消费者****" + number);

        flag = false;
        notifyAll();
    }
}