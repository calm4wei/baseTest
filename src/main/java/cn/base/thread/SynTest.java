package cn.base.thread;

class SynTest implements Runnable {

    private synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    private synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SynTest ss = new SynTest();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}