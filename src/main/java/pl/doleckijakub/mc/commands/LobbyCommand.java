package pl.doleckijakub.mc.commands;
import pl.doleckijakub.mc.common.MinigameManager;
import pl.doleckijakub.mc.minigames.Lobby;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.common.CommandInfo;
import pl.doleckijakub.mc.common.PluginCommand;

import javax.naming.InvalidNameException;
import java.security.InvalidKeyException;

@CommandInfo(name = "lobby")
public class LobbyCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        MinigameManager.playerJoinLobby(player);
    }

}