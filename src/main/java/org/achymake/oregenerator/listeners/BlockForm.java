package org.achymake.oregenerator.listeners;

import org.achymake.oregenerator.OreGenerator;
import org.achymake.oregenerator.handlers.MaterialHandler;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.plugin.PluginManager;

public class BlockForm implements Listener {
    private OreGenerator getInstance() {
        return OreGenerator.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private MaterialHandler getMaterialHandler() {
        return getInstance().getMaterialHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public BlockForm() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockForm(BlockFormEvent event) {
        if (!event.getBlock().getType().equals(Material.LAVA))return;
        if (!event.getNewState().getType().equals(Material.COBBLESTONE))return;
        if (getConfig().getBoolean("deepslate.enable")) {
            if (getConfig().getInt("deepslate.y") >= event.getBlock().getLocation().getBlockY()) {
                getMaterialHandler().setDeepOre(event.getNewState());
            } else getMaterialHandler().setOre(event.getNewState());
        } else getMaterialHandler().setOre(event.getNewState());
    }
}