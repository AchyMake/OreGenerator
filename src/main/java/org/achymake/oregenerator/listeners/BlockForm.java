package org.achymake.oregenerator.listeners;

import org.achymake.oregenerator.OreGenerator;
import org.achymake.oregenerator.handlers.OreHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.plugin.PluginManager;

public class BlockForm implements Listener {
    private OreGenerator getInstance() {
        return OreGenerator.getInstance();
    }
    private OreHandler getOreHandler() {
        return getInstance().getOreHandler();
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
        if (!event.getNewState().getBlock().getType().equals(Material.LAVA))return;
        getOreHandler().setOre(event.getNewState());
    }
}