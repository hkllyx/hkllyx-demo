package com.hkllyx.demo.basic.concurrent.others.timertask;

import com.hkllyx.demo.basic.concurrent.util.ExecutorUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1）Timer是用于线程调度任务以供将来在后台线程中执行的工具。
 * 任务可以安排为一次性执行，或者定期重复执行。
 * 2）TimerTask是可由定时器计划一次或多次执行的任务，实现了Runnable接口。
 *
 * @author HKLLY
 * @date 2019-07-13
 */
public class PrintDateTask extends TimerTask {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {
        /*
        Timer timer = new Timer();
        // 参数1：执行的任务
        // 参数2：延迟多少毫秒后开始执行
        // 参数3：多少毫秒执行一次
        timer.scheduleAtFixedRate(new PrintDateTask(), 0, 1000L);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
         */

        ScheduledExecutorService pool = ExecutorUtils.newScheduledThreadPool(5);
        pool.scheduleAtFixedRate(new PrintDateTask(), 0, 1L, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(5);
        pool.shutdown();
    }

    @Override
    public void run() {
        System.out.println(SIMPLE_DATE_FORMAT.format(new Date()));
    }
}
