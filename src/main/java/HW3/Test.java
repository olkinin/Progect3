package HW3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Test implements Lock {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 50; i++) {
                    counter.increment();
                    System.out.println("tread 1 -  " + counter.getValue());
                }
            } finally {
                lock.unlock();
            }
        });


        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 50; i++) {
                    counter.decrement();
                    System.out.println("tread 2 -  " + counter.getValue());
                }
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getValue());
    }
}
