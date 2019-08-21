package com.example.myview;


import android.util.TimeUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/8/2110:36
 * desc   :
 * package: Shop:
 */
public class ThreadPoolManager {
    private static ThreadPoolManager ourInstance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return ourInstance;
    }

    //把任务添加到队列；结构：链表
    private LinkedBlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>();

    public void execute(Runnable runnable) {
        if (runnable != null) {
            try {
                runnables.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //队列的任务加入到线程池
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        threadPoolExecutor.execute(runnable);
    }

    //拒绝策略
    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                runnables.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    //运行

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable runnable = null;
                //对列出
                try {
                    runnable = runnables.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runnable != null) {
                    threadPoolExecutor.execute(runnable);
                }
            }
        }
    };


}
