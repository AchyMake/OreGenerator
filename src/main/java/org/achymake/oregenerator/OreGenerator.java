package org.achymake.oregenerator;

import org.achymake.oregenerator.commands.*;
import org.achymake.oregenerator.data.*;
import org.achymake.oregenerator.handlers.*;
import org.achymake.oregenerator.listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public final class OreGenerator extends JavaPlugin {
    private static OreGenerator instance;
    private Message message;
    private MaterialHandler materialHandler;
    private RandomHandler randomHandler;
    private ScheduleHandler scheduleHandler;
    private UpdateChecker updateChecker;
    private BukkitScheduler bukkitScheduler;
    private PluginManager pluginManager;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message();
        materialHandler = new MaterialHandler();
        randomHandler = new RandomHandler();
        scheduleHandler = new ScheduleHandler();
        updateChecker = new UpdateChecker();
        bukkitScheduler = getServer().getScheduler();
        pluginManager = getServer().getPluginManager();
        commands();
        events();
        reload();
        sendInfo("Enabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        getScheduleHandler().disable();
    }
    private void commands() {
        new OreGeneratorCommand();
    }
    private void events() {
        new BlockForm();
        new PlayerJoin();
    }
    public void reload() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else reloadConfig();
    }
    public PluginManager getPluginManager() {
        return pluginManager;
    }
    public BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public ScheduleHandler getScheduleHandler() {
        return scheduleHandler;
    }
    public RandomHandler getRandomHandler() {
        return randomHandler;
    }
    public MaterialHandler getMaterialHandler() {
        return materialHandler;
    }
    public Message getMessage() {
        return message;
    }
    public static OreGenerator getInstance() {
        return instance;
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public void sendWarning(String message) {
        getLogger().warning(message);
    }
    public String name() {
        return getDescription().getName();
    }
    public String version() {
        return getDescription().getVersion();
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
}