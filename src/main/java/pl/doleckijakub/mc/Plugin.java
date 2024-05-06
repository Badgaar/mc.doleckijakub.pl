package pl.doleckijakub.mc;

import org.bukkit.plugin.java.JavaPlugin;
import pl.doleckijakub.mc.commands.ConfigureCommand;
import pl.doleckijakub.mc.commands.LobbyCommand;
import pl.doleckijakub.mc.commands.PlayCommand;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.PluginCommand;
import pl.doleckijakub.mc.managers.MinigameEventHandler;
import pl.doleckijakub.mc.util.ANSI;

import java.io.File;

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

        for (PluginCommand pluginCommand : new PluginCommand[] {
                new PlayCommand(),
                new LobbyCommand(),
                new ConfigureCommand()
        }) {
            String commandName = pluginCommand.getCommandInfo().name();

            getCommand(commandName).setExecutor(pluginCommand);
            getLogger().info(ANSI.GREEN + "Registered /" + commandName + ANSI.RESET);
        }

        getServer().getPluginManager().registerEvents(new MinigameEventHandler(), this);
    }

    @Override
    public void onDisable() {
        GameWorld.unloadAll();
    }

}
