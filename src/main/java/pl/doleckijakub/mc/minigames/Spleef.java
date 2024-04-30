package pl.doleckijakub.mc.minigames;

import org.bukkit.ChatColor;
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

    public Spleef() {
        super();
        this.gameState = GameState.LOBBY;
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

}
