package pl.doleckijakub.mc;

import org.bukkit.plugin.java.JavaPlugin;
import pl.doleckijakub.mc.commands.Test;
import pl.doleckijakub.mc.common.GameWorld;

import java.io.File;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;

    public static File getWorldsDirectory() {
        return new File(instance.getDataFolder(), "worlds");
    }

    @Override
    public void onEnable() {
        instance = this;

        getCommand("test").setExecutor(new Test());
    }

    @Override
    public void onDisable() {
        GameWorld.unloadAll();
    }

}
