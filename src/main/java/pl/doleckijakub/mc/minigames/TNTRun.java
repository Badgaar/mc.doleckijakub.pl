package pl.doleckijakub.mc.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;

import static org.bukkit.Bukkit.getServer;

public class TNTRun extends Minigame{


    public enum GameState {
        LOBBY,
        RUNNING,
        FINISHED;

        @Override
        public String toString() {
            switch (this) {
                case LOBBY:
                    return ChatColor.GREEN + "Lobby";
                case RUNNING:
                    return ChatColor.GOLD + "Running";
                case FINISHED:
                    return ChatColor.RED + "Finished";
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
                while (this.lobbyWorld == null) ;
                player.teleport(lobbyWorld.getWorld().getSpawnLocation());
            }
            break;
        }
    }

    @Override
    public void onPlayerJoin(Player player) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Player " + player.getName() + " joined TNT Run game " + getId());
    }

    @Override
    public void onPlayerLeave(Player player) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Player " + player.getName() + " left TNT Run game " + getId());
    }

    @Override
    public World getWorld() {
        switch (gameState) {
            case LOBBY:
                return lobbyWorld.getWorld();
            default:
                throw new RuntimeException("unimplemented");
        }
    }

    //1s=20tic
    public int secondsToStart = 10;
    String stringedSecondsToStart = ""+secondsToStart;

    //Centered countdown
    public void sendTimerMessage(Player player,int secondsToStart) {
        int messageLength = stringedSecondsToStart.length();
        int centerMessage = 154 / 2;
        int messageStart = centerMessage - (messageLength / 2);
        String spaces = "";
        for (int i = 0; i < messageStart; i++) {
            spaces += " ";
        }
        player.sendMessage(spaces + secondsToStart);
    }

    //Start countdown
    //Weź proszę popraw tego zjebanego syntaxa w 105
    private void startCountdown() {
        Bukkit.getScheduler().runTaskTimer(TNTRun,() -> {
            if (secondsToStart <= 0) {
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendTimerMessage(player, secondsToStart);
            }
            secondsToStart--;
        }, 0L, 20L);
    }

    //Block remover
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        //Get blocks (below player and one under it)
        Block blockBelowPlayer = Player.getLocation().substract(0, 1, 0).getblock();
        Block blockBelowBlockBelowPlayer = Player.getLocation().substract(0, 2, 0).getblock();

        //If player is on the ground remove 2 block underneath his feet
        if (blockBelowPlayer.getType() != Material.AIR && secondsToStart != 0) {
            blockBelowPlayer.setType(Material.AIR);
            blockBelowBlockBelowPlayer.setType(Material.AIR);
        } else super.onPlayerMoveEvent(e);
    }
}