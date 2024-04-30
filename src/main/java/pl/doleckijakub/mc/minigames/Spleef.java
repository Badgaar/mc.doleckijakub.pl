package pl.doleckijakub.mc.minigames;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;

public class Spleef extends Minigame {

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

    private GameWorld lobbyWorld;
    private GameWorld gameWorld;

    public Spleef() {
        super();
        this.gameState = GameState.LOBBY;
        this.lobbyWorld = new GameWorld("spleef_lobby");
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    @Override
    public void onPlayerJoin(Player player) {
        switch (gameState) {
            case LOBBY: {
                while (this.lobbyWorld == null) {
                    player.sendMessage("Loading...");
                }

                player.teleport(lobbyWorld.getWorld().getSpawnLocation());
            } break;
        }
    }

    @Override
    public void onPlayerLeave(Player player) {

    }

}
