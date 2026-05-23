package net.jrz.tracker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 如何快速定位到五分钟内重复登录了两次的qq号
// 其实这个类似限流算法 ---> 滑动窗口两种实现（1. 队列，要存储时间戳；2. 桶窗口法，每个小桶里面计数）
public class LoginTracker {
    private final Map<Long, Queue<Long>> loginMap = new HashMap<>();
    private final long TIME_WINDOW = 5 * 60 * 1000; // 5分钟

    public boolean isDuplicateLogin(long qqNumber, long currentTime){
        Queue<Long> timestamps = loginMap.getOrDefault(qqNumber, new LinkedList<>());

        // 首先清除掉5分钟之前的记录
        while (!timestamps.isEmpty() && currentTime - timestamps.peek() > TIME_WINDOW){
            timestamps.poll();
        }

        boolean isDuplicate = !timestamps.isEmpty();
        timestamps.offer(currentTime);
        loginMap.put(qqNumber, timestamps);
        return isDuplicate;
    }
}
