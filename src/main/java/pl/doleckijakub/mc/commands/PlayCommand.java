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

@CommandInfo(name = "play")
public class PlayCommand extends PluginCommand {

    @Override
    public void execute(Player player, String[] args) {
        switch (args.length) {
            case 0: {
                player.sendMessage(ChatColor.GOLD + "Availible minigames" + ChatColor.RESET + ":");

                int i = 0;
                for (String minigameName : MinigameManager.getMinigameNames()) {
                    TextComponent message = new TextComponent(++i + ": " + ChatColor.GREEN + minigameName);
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + getCommandInfo().name() + " " + minigameName));
                    player.spigot().sendMessage(message);
                }
            } break;
            case 1: {
                try {
                    String minigameName = args[0].toLowerCase();

                    Map<UUID, Minigame> minigames = MinigameManager.getCurrentGames(minigameName);
                    if (minigames.isEmpty()) {
                        TextComponent[] message = new TextComponent[] {
                                new TextComponent("There are no currently open " + minigameName + " games, but you can start one by issuing "),
                                new TextComponent(ChatColor.AQUA + "/" + getCommandInfo().name() + " " + minigameName + " new"),
                        };

                        message[1].setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + getCommandInfo().name() + " " + minigameName + " new"));

                        player.spigot().sendMessage(message[0], message[1]);
                    } else {
                        player.sendMessage(ChatColor.GOLD + "Current");
                        for (UUID gameId : minigames.keySet()) {
                            TextComponent message = new TextComponent(ChatColor.GREEN + minigameName + "_" + gameId + ChatColor.RESET + ": " + minigames.get(gameId).getGameStateString() + ChatColor.RESET + " (" + minigames.get(gameId).getPlayerCount() + " players)");
                            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + getCommandInfo().name() + " " + minigameName + " " + gameId));
                            player.spigot().sendMessage(message);
                        }
                    }
                } catch (InvalidNameException e) {
                    TextComponent[] message = new TextComponent[] {
                            new TextComponent(ChatColor.RED + args[0] + " is not a valid minigame, list valid minigames by running "),
                            new TextComponent(ChatColor.AQUA + "/" + getCommandInfo().name()),
                    };

                    message[1].setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + getCommandInfo().name()));

                    player.spigot().sendMessage(message[0], message[1]);
                }
            } break;
            case 2: {
                try {
                    String minigameName = args[0].toLowerCase();

//                    Map<UUID, Minigame> minigames = MinigameManager.getCurrentGames(minigameName);

                    if (args[1].equalsIgnoreCase("new")) {
                        MinigameManager.playerJoinNewGame(player, minigameName);
                    } else {
                        throw new RuntimeException("joining games by uuid is not implemented yet");
                    }
                } catch (InvalidNameException e) {
                    TextComponent[] message = new TextComponent[] {
                            new TextComponent(ChatColor.RED + args[0] + " is not a valid minigame, list valid minigames by running "),
                            new TextComponent(ChatColor.AQUA + "/" + getCommandInfo().name()),
                    };

                    message[1].setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + getCommandInfo().name()));

                    player.spigot().sendMessage(message[0], message[1]);
                }
            } break;
        }
    }

}
