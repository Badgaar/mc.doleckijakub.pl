package pl.doleckijakub.mc.common;

import org.bukkit.*;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.Plugin;
import pl.doleckijakub.mc.util.ANSI;
import pl.doleckijakub.mc.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameWorld {

    private final static Set<GameWorld> INSTANCES = new HashSet<>();
    private final static File WORLDS_DIRECTORY = Plugin.getWorldsDirectory();

    private final String worldName;

    private final File directory;
    private final World world;

    public GameWorld(String worldName) {
        this.worldName = worldName;

        if (!WORLDS_DIRECTORY.exists()) WORLDS_DIRECTORY.mkdirs();

        File source = new File(
                WORLDS_DIRECTORY,
                worldName
        );

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

    public static void unloadAll() {
        for (GameWorld gameWorld : INSTANCES) {
            gameWorld.unload();
        }
    }

}
