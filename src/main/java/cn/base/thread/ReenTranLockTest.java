package cn.base.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁比较：ReenTranLock 与 synchronized
 * Created by fengwei on 17/3/17.
 */
class ReenTranLockTest implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    private void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReenTranLockTest test = new ReenTranLockTest();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();

    }

}
