package net.jrz.currentLimit;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GateWay gateWay = new OptimizeSlidingWindowLimiter(2, 1000, 10);

        new Thread(() -> {
            if (gateWay.allow()) {
                System.out.printf("%s executing successfully!\n", Thread.currentThread().getName());
            } else {
                System.out.printf("%s executing failed!\n", Thread.currentThread().getName());
            }
        }, "t1").start();

        new Thread(() -> {
            if (gateWay.allow()) {
                System.out.printf("%s executing successfully!\n", Thread.currentThread().getName());
            } else {
                System.out.printf("%s executing failed!\n", Thread.currentThread().getName());
            }
        }, "t2").start();

        new Thread(() -> {
            if (gateWay.allow()) {
                System.out.printf("%s executing successfully!\n", Thread.currentThread().getName());
            } else {
                System.out.printf("%s executing failed!\n", Thread.currentThread().getName());
            }
        }, "t3").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }

        new Thread(() -> {
            if (gateWay.allow()) {
                System.out.printf("%s executing successfully!\n", Thread.currentThread().getName());
            } else {
                System.out.printf("%s executing failed!\n", Thread.currentThread().getName());
            }
        }, "t4").start();
    }
}
