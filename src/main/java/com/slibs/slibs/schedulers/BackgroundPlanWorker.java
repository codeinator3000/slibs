package com.slibs.slibs.schedulers;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

public abstract class BackgroundPlanWorker implements Scheduler {
    private ThreadPoolTaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledTask;
    private boolean isInit = false;

    public Boolean isInit() {
        return isInit;
    }

    public void init() {
        if (isInit) return;
        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.initialize();
        isInit = true;
    }

    /**
     * Создает Cron-расписание запусков функции.
     * @return расписание.
     */
    protected abstract CronTrigger cron();

    /**
     * Функция, которая будет вызываться фоновым процессом.
     */
    protected abstract void function();
    @Override
    public boolean start() {
        if (!isInit) {
            return false;
        }
        if (scheduledTask == null || scheduledTask.isCancelled()) {
            scheduledTask = taskScheduler.schedule(
                    this::function,
                    cron()
            );
            return true;
        }
        return false;
    }

    @Override
    public boolean end() {
        if (!isInit) {
            return false;
        }
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
            scheduledTask = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean getActiveStatus() {
        return scheduledTask != null;
    }
}
