package net.jrz.currentLimit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

// 滑动窗口法： 需要利用队列，然后要记录时间戳
public class SlidingWindowLimiter implements GateWay{
    private Queue<Long> queue = new LinkedList<>();
    private int LIMIT;
    private ReentrantLock lock = new ReentrantLock();

    public SlidingWindowLimiter(int LIMIT) {
        this.LIMIT = LIMIT;
    }

    @Override
    public boolean allow(){
        lock.lock();
        try {
            long now = System.currentTimeMillis();

            while (!queue.isEmpty() && now - queue.peek() > 1000){
                queue.poll(); // 弹出上一个时间窗口的，同时还解决了时间突刺
            }

            if (queue.size() >= LIMIT){
                return false;
            }

            queue.offer(now);
            return true;
        } finally {
            lock.unlock();
        }
    }
}
