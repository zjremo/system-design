package net.jrz.currentLimit;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

// 滑动窗口法: 不再记录时间戳，分桶，记录每个小桶里面的请求数
public class OptimizeSlidingWindowLimiter implements GateWay {
    private static class Bucket {
        long bucketStart;
        int count;

        public Bucket() {
        }

        public Bucket(long bucketStart, int count) {
            this.bucketStart = bucketStart;
            this.count = count;
        }
    }

    private int LIMIT;
    private ReentrantLock lock = new ReentrantLock();
    private Bucket[] buckets;
    private int windowSize;
    private int bucketsNum;
    private int bucketSize;

    public OptimizeSlidingWindowLimiter(int LIMIT, int windowSize, int bucketsNum) {
        this.LIMIT = LIMIT;
        this.windowSize = windowSize;
        this.bucketsNum = bucketsNum;

        this.bucketSize = windowSize / bucketsNum;
        buckets = new Bucket[bucketsNum];
        for (int i = 0; i < bucketsNum; ++i) {
            buckets[i] = new Bucket(0, 0);
        }
    }

    @Override
    public boolean allow() {
        lock.lock();
        try {
            long now = System.currentTimeMillis();
            int idx = (int) ((now / bucketSize) % bucketsNum);

            Bucket curBucket = buckets[idx]; // 获取当前时间所在的桶
            long curBucketStart = now - (now % bucketSize);

            if (curBucketStart != curBucket.bucketStart) { // 此时还是上一个1s的
                curBucket.bucketStart = curBucketStart;
                curBucket.count = 0;
            }

            int total = Arrays.stream(buckets)
                    .filter(b -> now - b.bucketStart <= 1000)
                    .mapToInt(b -> b.count)
                    .sum();
            if (total < LIMIT) {
                ++curBucket.count;
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }
}
