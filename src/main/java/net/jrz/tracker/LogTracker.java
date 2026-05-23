package net.jrz.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogTracker {
    public void processLogs(Log[] logs) {
        List<Event> events = new ArrayList<>();
        for (Log log : logs) {
            events.add(new Event(log.loginTime, 1)); // 上线是 +1
            events.add(new Event(log.logoutTime, -1)); // 下线是 -1
        }
        Collections.sort(events);

        // 扫描时间线
        int curOnline = 0; // 当前在线人数
        long maxOnline = 0; // 有史以来最多的人数
        long maxStart = -1; // 最大人数开始时间
        long maxEnd = -1; // 最大人数结束时间
        long lastTime = -1; // 上一个时间点
        long longestDuration = 0; // 最大在线人数持续的时间

        for (Event e : events) {
            if (lastTime != -1 && curOnline == maxOnline) { // 当前人数是最大人数
                long duration = e.time - lastTime;
                if (duration > longestDuration) { // 时间更新
                    longestDuration = duration;
                    maxStart = lastTime;
                    maxEnd = e.time;
                }
            }
            curOnline += e.delta;
            if (curOnline > maxOnline) {
                maxOnline = curOnline;
                maxStart = e.time;
                longestDuration = 0; // 重置最长区间
            }

            lastTime = e.time;
        }

        System.out.println("最大在线人数: " + maxOnline);
        System.out.println("最长持续时间: " + (maxEnd - maxStart) + "秒");
    }
}

class Log {
    long loginTime;
    long logoutTime;
}

class Event implements Comparable<Event> {
    long time;
    int delta;

    public Event(long time, int delta) {
        this.time = time;
        this.delta = delta;
    }

    @Override
    public int compareTo(Event o) {
        if (this.time != o.time)
            return (int) (this.time - o.time);
        return o.delta - this.delta;
    }
}


