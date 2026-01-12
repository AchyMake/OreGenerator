package org.achymake.oregenerator.handlers;

import org.achymake.oregenerator.OreGenerator;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MaterialHandler {
    private OreGenerator getInstance() {
        return OreGenerator.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private RandomHandler getRandomHandler() {
        return getInstance().getRandomHandler();
    }
    public Material get(String materialName) {
        return Material.getMaterial(materialName.toUpperCase());
    }
    public ArrayList<Map.Entry<String, Double>> getOres() {
        var levels = new HashMap<String, Double>();
        var section = getConfig().getConfigurationSection("chances");
        if (section != null) {
            section.getKeys(false).forEach(level -> {
                if (!getConfig().getBoolean("chances." + level + ".enable"))return;
                var chance = getConfig().getDouble("chances." + level + ".chance");
                if (chance > 0) {
                    levels.put(level, chance);
                }
            });
        }
        var list = new ArrayList<>(levels.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        return list;
    }
    public void setOre(BlockState blockState) {
        var ores = getOres();
        if (ores.isEmpty())return;
        var world = blockState.getWorld();
        if (!getConfig().getStringList("worlds").contains(world.getName()))return;
        for (var ore : ores) {
            if (!getRandomHandler().isTrue(ore.getValue()))return;
            blockState.setType(get(ore.getKey()));
            blockState.update(true);
        }
    }
    private ArrayList<Map.Entry<String, Double>> getDeepOres() {
        var levels = new HashMap<String, Double>();
        var section = getConfig().getConfigurationSection("deepslate.chances");
        if (section != null) {
            section.getKeys(false).forEach(level -> {
                if (!getConfig().getBoolean("deepslate.chances." + level + ".enable"))return;
                var chance = getConfig().getDouble("deepslate.chances." + level + ".chance");
                if (chance > 0) {
                    levels.put(level, chance);
                }
            });
        }
        var list = new ArrayList<>(levels.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        return list;
    }
    public void setDeepOre(BlockState blockState) {
        var ores = getDeepOres();
        if (ores.isEmpty())return;
        var world = blockState.getWorld();
        if (!getConfig().getStringList("worlds").contains(world.getName()))return;
        for (var ore : ores) {
            if (!getRandomHandler().isTrue(ore.getValue()))return;
            blockState.setType(get(ore.getKey()));
            blockState.update(true);
        }
    }
}