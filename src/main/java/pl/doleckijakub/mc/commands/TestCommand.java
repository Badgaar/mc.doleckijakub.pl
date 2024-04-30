package pl.doleckijakub.mc.commands;

import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.common.CommandInfo;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.PluginCommand;

@CommandInfo(name = "test", permissions = "op")
public class TestCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("unload")) {
                GameWorld.unloadAll();
            } else if (args[0].equals("list")) {
                player.sendMessage(ChatColor.RESET + "Worlds:");

                int i = 0;
                for (World world : Bukkit.getWorlds()) {
                    TextComponent message = new TextComponent(String.format("%s%d%s: %s",
                            ChatColor.GOLD,
                            ++i,
                            ChatColor.RESET,
                            world.getName()
                    ));

                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/test " + world.getName()));
                    player.spigot().sendMessage(message);
                }
            } else {
                World world = Bukkit.getWorld(args[0]);
                if (world == null) world = new GameWorld(args[0]).getWorld();

                player.teleport(world.getSpawnLocation());
            }
        } else if (args.length == 0) {
            player.teleport(Bukkit.getWorld("lobby").getSpawnLocation());
        } else {
            player.sendMessage(ChatColor.RED + "Wrong usage");
        }
    }

}
