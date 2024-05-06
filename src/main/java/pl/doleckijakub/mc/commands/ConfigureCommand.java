package pl.doleckijakub.mc.commands;

import org.bukkit.entity.Player;
import pl.doleckijakub.mc.common.CommandInfo;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.common.MinigameManager;
import pl.doleckijakub.mc.common.PluginCommand;
import pl.doleckijakub.mc.minigames.WorldConfiguration;

@CommandInfo(name = "configure", permissions = "op")
public class ConfigureCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        switch (args[0]) {
            case "join": {
                MinigameManager.playerJoinOwnMinigame(player, new WorldConfiguration(args[1]));
            } break;
            case "get": {
                Minigame minigame = MinigameManager.getMinigameByPlayer(player);
                if (!(minigame instanceof WorldConfiguration)) throw new RuntimeException("Not in configuration");
                WorldConfiguration worldConfiguration = (WorldConfiguration) minigame;

                player.sendMessage(worldConfiguration.getConfigAsString());
            } break;
            case "set": {
                Minigame minigame = MinigameManager.getMinigameByPlayer(player);
                if (!(minigame instanceof WorldConfiguration)) throw new RuntimeException("Not in configuration");
                WorldConfiguration worldConfiguration = (WorldConfiguration) minigame;

                worldConfiguration.setConfigString(args[1], args[2]);
                player.sendMessage(worldConfiguration.getWorldName() + "." + args[1] + " is now " + worldConfiguration.getConfigString(args[1]));
            } break;
        }

    }

}
