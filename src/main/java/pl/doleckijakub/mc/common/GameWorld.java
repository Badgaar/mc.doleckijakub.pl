package pl.doleckijakub.mc.common;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.Plugin;
import pl.doleckijakub.mc.util.ANSI;
import pl.doleckijakub.mc.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.Set;

public class GameWorld {

    private final static Set<GameWorld> INSTANCES = new HashSet<>();
    private final static File WORLDS_DIRECTORY = Plugin.getWorldsDirectory();

    private final String worldName;

    private final File directory;
    private final World world;

    private final File configFile;
    private final FileConfiguration config;

    public GameWorld(String worldName) {
        this.worldName = worldName;

        if (!WORLDS_DIRECTORY.exists()) WORLDS_DIRECTORY.mkdirs();

        File source = new File(
                WORLDS_DIRECTORY,
                worldName
        );

        { // world

            if (!source.exists()) throw new RuntimeException("Missing directory: " + source.getAbsolutePath());

            this.directory = new File(
                    Bukkit.getWorldContainer().getParentFile(),
                    "gameworld_" + worldName + "_" + System.currentTimeMillis()
            );

            try {
                FileUtils.copy(source, directory);
            } catch (IOException e) {
                throw new RuntimeException(
                        "Failed to copy " + source.getAbsolutePath() +
                                " to " + directory.getAbsolutePath() +
                                ", because: " + e
                );
            }

            this.world = Bukkit.createWorld(
                    new WorldCreator(directory.getName())
            );

            world.setAutoSave(false);

        }

        { // config

            configFile = new File(source, "gameworld_config.yml");
//            if (!configFile.exists()) throw new RuntimeException("File " + configFile.getAbsolutePath() + " not found");

            config = YamlConfiguration.loadConfiguration(configFile);

        }

        INSTANCES.add(this);
    }

    public void unload() {
        Bukkit.getLogger().info(ANSI.BLUE + "Unloading " + directory.getName() + ANSI.RESET);

        for (Player player : world.getPlayers()) MinigameManager.playerJoinLobby(player);
        Bukkit.unloadWorld(world, false);
        FileUtils.delete(directory);

        INSTANCES.remove(this);
    }

    public String getWorldName() {
        return worldName;
    }

    public World getWorld() {
        return world;
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConfigAsString() {
        return config.saveToString();
    }

    public String getConfigString(String path) {
        return config.getString(path);
    }

    public void setConfigString(String path, String value) {
        config.set(path, value);
        saveConfig();
    }

    public int getConfigInt(String path) {
        return config.getInt(path);
    }

    public void setConfigInt(String path, int value) {
        config.set(path, value);
        saveConfig();
    }

    public Location getConfigLocation(String path) {
        try {
            return new Location(
                    world,
                    (Double) config.get(path + ".x"),
                    (Double) config.get(path + ".y"),
                    (Double) config.get(path + ".z")
            );
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void setConfigLocation(String path, Location value) {
        if (!value.getWorld().equals(world)) throw new RuntimeException("location not in the same world as GameWorld's world");
        config.set(path + ".x", value.getX());
        config.set(path + ".y", value.getY());
        config.set(path + ".z", value.getZ());
        saveConfig();
    }

    public static void unloadAll() {
        for (GameWorld gameWorld : INSTANCES) {
            gameWorld.unload();
        }
    }

}
