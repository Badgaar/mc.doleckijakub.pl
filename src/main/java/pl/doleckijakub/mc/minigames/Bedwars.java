package pl.doleckijakub.mc.minigames;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;

import java.util.*;
import java.util.stream.Collectors;

public class Bedwars extends Minigame {

    public static class Solo  extends Bedwars { public Solo()  { super(GameType.SOLO);  } }
//    public static class Duo   extends Bedwars { public Duo()   { super(GameType.DUO);   } }
//    public static class Teams extends Bedwars { public Teams() { super(GameType.TEAMS); } }

    public enum GameType {
        SOLO,
        DUO,
        TEAMS;

        public int getTeamSize() {
            switch (this) {
                case SOLO:
                    return 1;
                case DUO:
                    return 2;
                case TEAMS:
                    return 4;
            }

            throw new IllegalStateException("unreachable");
        }
    }

    public enum TeamColor {
        RED,
        BLUE,
        ORANGE,
        MAGENTA,
        AQUA,
        YELLOW,
        GREEN,
        GRAY;

        public ChatColor getChatColor() {
            switch (this) {
                case RED:     return ChatColor.DARK_RED;
                case BLUE:    return ChatColor.DARK_BLUE;
                case ORANGE:  return ChatColor.GOLD;
                case MAGENTA: return ChatColor.LIGHT_PURPLE;
                case AQUA:    return ChatColor.BLUE;
                case YELLOW:  return ChatColor.YELLOW;
                case GREEN:   return ChatColor.DARK_GREEN;
                case GRAY:    return ChatColor.GRAY;
            }

            throw new RuntimeException("unimplemented");
        }

        public Wool getWool() {
            switch (this) {
                case RED:     return new Wool(DyeColor.RED);
                case BLUE:    return new Wool(DyeColor.BLUE);
                case ORANGE:  return new Wool(DyeColor.ORANGE);
                case MAGENTA: return new Wool(DyeColor.MAGENTA);
                case AQUA:    return new Wool(DyeColor.LIGHT_BLUE);
                case YELLOW:  return new Wool(DyeColor.YELLOW);
                case GREEN:   return new Wool(DyeColor.GREEN);
                case GRAY:    return new Wool(DyeColor.SILVER);
                default: throw new RuntimeException("unimplemented");
            }
        }

        public ItemStack getWoolItemStack() {
            return getWoolItemStack(1);
        }

        public ItemStack getWoolItemStack(int amount) {
            ItemStack result = getWool().toItemStack();
            result.setAmount(amount);
            return result;
        }

        public static TeamColor fromWoolItem(ItemStack itemStack) {
            return Arrays.stream(values()).filter(teamColor -> teamColor.isCorrectColorWool(itemStack)).findFirst().get();
        }

        private boolean isCorrectColorWool(ItemStack itemStack) {
            return itemStack.isSimilar(getWoolItemStack());
        }

    }

    private final GameType gameType;

    public enum GameState {
        LOBBY,
        RUNNING,
        FINISHED;

        @Override
        public String toString() {
            switch (this) {
                case LOBBY: return ChatColor.GREEN + "Lobby";
                case RUNNING: return ChatColor.GOLD + "Running";
                case FINISHED: return ChatColor.RED + "Finished";
            }

            return null;
        }
    }

    private GameState gameState;

    public class Team {
        public Team(List<Player> members, Location bed, Location shop, Location upgrades, Location summoner) {
            log("members = " + String.join(", ", members.stream().map(Player::getName).collect(Collectors.joining())));
            log("bed = " + bed);
            log("shop = " + shop);
            log("upgrades = " + upgrades);
            log("summoner = " + summoner);
        }
    }

    private final List<Team> teams;

    private final GameWorld lobbyWorld;
    private final GameWorld gameWorld;

    public Bedwars(GameType gameType) {
        this.gameType = gameType;

        this.lobbyWorld = new GameWorld("bedwars_lobby");
        this.gameWorld = new GameWorld("bedwars_map_treasure_island");

        List<Player> players = new ArrayList<>(getPlayers());
        int p = 0;

        this.teams = new ArrayList<>();
        for (TeamColor teamColor : TeamColor.values()) {
            Location bed = gameWorld.getConfigLocation("team." + teamColor.toString().toLowerCase() + ".bed");
            if (bed == null) continue;

            Location shop = gameWorld.getConfigLocation("team." + teamColor.toString().toLowerCase() + ".bed");
            if (shop == null) continue;

            Location upgrades = gameWorld.getConfigLocation("team." + teamColor.toString().toLowerCase() + ".bed");
            if (upgrades == null) continue;

            Location summoner = gameWorld.getConfigLocation("team." + teamColor.toString().toLowerCase() + ".bed");
            if (summoner == null) continue;

            List<Player> members = new ArrayList<>();

            for (int i = 0; i < gameType.getTeamSize(); i++) {
                members.add(players.get(p++));
                if (p == players.size() - 1) break;
            }

            teams.add(new Team(
                    members,
                    bed,
                    shop,
                    upgrades,
                    summoner
            ));

            if (p == players.size() - 1) break;
        }

    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    @Override
    public void teleportPlayer(Player player) {
        switch (gameState) {
            case LOBBY:
            case FINISHED:
                player.teleport(lobbyWorld.getWorld().getSpawnLocation());
                break;
            case RUNNING:

                break;
        }
    }

    @Override
    public void onPlayerJoin(Player player) {
        throw new IllegalStateException("unimplemented");
    }

    @Override
    public void onPlayerLeave(Player player) {
        throw new IllegalStateException("unimplemented");
    }

    @Override
    public void onPlayerChatMessage(Player player, String message) {
        broadcastMessage(player.getName() + ChatColor.GRAY + " » " + ChatColor.RESET + message);
    }

    @Override
    public void cleanUp() {
        throw new IllegalStateException("unimplemented");
    }

    @Override
    public World getWorld() {
        throw new IllegalStateException("unimplemented");
    }

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        throw new IllegalStateException("unimplemented");
    }

}
