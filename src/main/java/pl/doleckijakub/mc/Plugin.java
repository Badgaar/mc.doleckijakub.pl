package pl.doleckijakub.mc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.PluginCommand;
import pl.doleckijakub.mc.managers.MinigameEventHandler;
import pl.doleckijakub.mc.util.ANSI;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    public static File getWorldsDirectory() {
        return new File(instance.getDataFolder(), "worlds");
    }

    @Override
    public void onEnable() {
        instance = this;
        String packageName = getClass().getPackage().getName();

        for (Class<? extends PluginCommand> clazz : new Reflections(packageName + ".commands").getSubTypesOf(PluginCommand.class)) {
            try {
                PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                String commandName = pluginCommand.getCommandInfo().name();

                getCommand(commandName).setExecutor(pluginCommand);
                getLogger().info(ANSI.GREEN + "Registered /" + commandName + ANSI.RESET);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        getServer().getPluginManager().registerEvents(new MinigameEventHandler(), this);
    }

    @Override
    public void onDisable() {
        GameWorld.unloadAll();
    }

}
