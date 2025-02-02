package org.achymake.oregenerator.handlers;

import org.achymake.oregenerator.OreGenerator;
import org.bukkit.scheduler.BukkitScheduler;

public class ScheduleHandler {
    private OreGenerator getInstance() {
        return OreGenerator.getInstance();
    }
    private BukkitScheduler getScheduler() {
        return getInstance().getBukkitScheduler();
    }
    public int runLater(Runnable runnable, long timer) {
        return getScheduler().runTaskLater(getInstance(), runnable, timer).getTaskId();
    }
    public int runAsynchronously(Runnable runnable) {
        return getScheduler().runTaskAsynchronously(getInstance(), runnable).getTaskId();
    }
    public boolean isQueued(int taskID) {
        return getScheduler().isQueued(taskID);
    }
    public void cancel(int taskID) {
        getScheduler().cancelTask(taskID);
    }
    public void cancelAll() {
        getScheduler().cancelTasks(getInstance());
    }
}