package pl.doleckijakub.mc.minigames;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.doleckijakub.mc.common.Minigame;

public class Bedwars extends Minigame {

    public static class Solo  extends Bedwars { public Solo()  { super(GameType.SOLO);  } }
    public static class Duo   extends Bedwars { public Duo()   { super(GameType.DUO);   } }
    public static class Teams extends Bedwars { public Teams() { super(GameType.TEAMS); } }

    public enum GameType {
        SOLO,
        DUO,
        TEAMS;
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

    public Bedwars(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    @Override
    public void teleportPlayer(Player player) {
        throw new IllegalStateException("unimplemented");
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
