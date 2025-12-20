package org.achymake.oregenerator.handlers;

import org.achymake.oregenerator.OreGenerator;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public class OreHandler {
    private OreGenerator getInstance() {
        return OreGenerator.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private RandomHandler getRandomHandler() {
        return getInstance().getRandomHandler();
    }
    private Set<Map.Entry<String, Double>> getOres() {
        var levels = new HashMap<String, Double>();
        getConfig().getConfigurationSection("chances").getKeys(false).forEach(level -> {
            if (!getConfig().getBoolean("chances." + level + ".enable"))return;
            levels.put(level, getConfig().getDouble("chances." + level + ".chance"));
        });
        var list = new ArrayList<>(levels.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        var result = new LinkedHashMap<String, Double>();
        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for (var entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result.entrySet();
    }
    public void setOre(BlockState blockState) {
        var ores = getOres();
        if (ores.isEmpty())return;
        if (!getConfig().getStringList("worlds").contains(blockState.getWorld().getName()))return;
        for (var ore : ores) {
            if (!getRandomHandler().isTrue(ore.getValue()))return;
            blockState.setType(Material.valueOf(ore.getKey()));
            blockState.update(true);
        }
    }
    private Set<Map.Entry<String, Double>> getDeepOres() {
        var levels = new HashMap<String, Double>();
        getConfig().getConfigurationSection("deepslate.chances").getKeys(false).forEach(level -> {
            if (!getConfig().getBoolean("deepslate.chances." + level + ".enable"))return;
            levels.put(level, getConfig().getDouble("deepslate.chances." + level + ".chance"));
        });
        var list = new ArrayList<>(levels.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        var result = new LinkedHashMap<String, Double>();
        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for (var entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result.entrySet();
    }
    public void setDeepOre(BlockState blockState) {
        var ores = getDeepOres();
        if (ores.isEmpty())return;
        if (!getConfig().getStringList("worlds").contains(blockState.getWorld().getName()))return;
        for (var ore : ores) {
            if (!getRandomHandler().isTrue(ore.getValue()))return;
            blockState.setType(Material.valueOf(ore.getKey()));
            blockState.update(true);
        }
    }
}