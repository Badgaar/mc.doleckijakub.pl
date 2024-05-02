package pl.doleckijakub.mc.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;

public class TNTRun extends Minigame {

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

    public TNTRun() {
        super();
        this.gameState = GameState.LOBBY;
        this.lobbyWorld = new GameWorld("tnt_run_lobby");
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    @Override
    public void teleportPlayer(Player player) {
        switch (gameState) {
            case LOBBY: {
                while (this.lobbyWorld == null);
                player.teleport(lobbyWorld.getWorld().getSpawnLocation());
            } break;
        }
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onPlayerLeave(Player player) {

    }

    @Override
    public void cleanUp() {
        throw new RuntimeException("todo");
    }

    @Override
    public World getWorld() {
        switch (gameState) {
            case LOBBY: return lobbyWorld.getWorld();
            default: throw new RuntimeException("unimplemented");
        }
    }

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        e.getEntity().spigot().respawn();
        // TODO: zostawiam tobie Wojtuś <3
    }
}