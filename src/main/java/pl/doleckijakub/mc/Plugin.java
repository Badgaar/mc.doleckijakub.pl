package pl.doleckijakub.mc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.broadcastMessage("test");
    }

    @Override
    public void onDisable() {

    }
}
