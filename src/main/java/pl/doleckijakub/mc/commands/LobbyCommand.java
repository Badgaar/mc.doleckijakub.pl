package pl.doleckijakub.mc.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.common.CommandInfo;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.common.MinigameManager;
import pl.doleckijakub.mc.common.PluginCommand;

import javax.naming.InvalidNameException;
import java.util.Map;
import java.util.UUID;

public class LobbyCommand {

    @CommandInfo(name = "lobby")
    public class PlayCommand extends PluginCommand {

        @Override
        public void tp(Player player, String[] args) {
            player.teleport(lobby.getInstance().getWorld().getSpawnLocation());
        }

    }

}
