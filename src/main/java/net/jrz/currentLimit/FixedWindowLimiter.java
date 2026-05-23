package net.jrz.currentLimit;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FixedWindowLimiter implements GateWay {
    private AtomicInteger counter = new AtomicInteger(0);
    private long curWindowStart = System.currentTimeMillis();
    private int LIMIT;
    private ReentrantLock lock = new ReentrantLock();

    public FixedWindowLimiter(int LIMIT) {
        this.LIMIT = LIMIT;
    }

    @Override
    public boolean allow(){
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            if (now - curWindowStart > 1000){// 上一个1s窗口已经结束
                curWindowStart = now;
                counter.set(0);
            }
            return counter.incrementAndGet() <= LIMIT;
        } finally {
            lock.unlock();
        }
    }
}
