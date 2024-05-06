package pl.doleckijakub.mc.commands;
import pl.doleckijakub.mc.common.MinigameManager;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.common.CommandInfo;
import pl.doleckijakub.mc.common.PluginCommand;

@CommandInfo(name = "lobby")
public class LobbyCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        MinigameManager.playerJoinLobby(player);
    }

}