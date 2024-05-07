package pl.doleckijakub.mc.minigames;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.doleckijakub.mc.Plugin;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.util.PlayerUtil;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TNTRun extends Minigame{

    private static final int MIN_PLAYERS = 2;
    private static final int COUNTDOWN_LENGTH = 10;

    public enum GameState {
        WAITING,
        RUNNING,
        FINISHED;


        @Override
        public String toString() {
            switch (this) {
                case WAITING:
                    return ChatColor.GREEN + "Waiting";
                case RUNNING:
                    return ChatColor.GOLD + "Running";
                case FINISHED:
                    return ChatColor.RED + "Finished";
            }

            return null;
        }

    }
    private GameState gameState;
    private Player winner;
    private GameWorld gameWorld;


    public TNTRun() {
        super();
        this.gameState = GameState.WAITING;
        this.gameWorld = new GameWorld("tntrun_map_0");
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    @Override
    public void teleportPlayer(Player player) {
        player.teleport(gameWorld.getWorld().getSpawnLocation());
    }

    private void setGameState(GameState newgamestate){
        switch (newgamestate) {
            case WAITING: throw new IllegalStateException();
            case RUNNING: {
                // countdown 
                broadcastSound(Sound.ENDERDRAGON_GROWL, 1, 2);
            }
            case FINISHED: {
                broadcastSound(Sound.GHAST_MOAN, 1, 2);
            }
        }
    }

    @Override
    public void onPlayerJoin(Player player) {
        if(getPlayerCount()==MIN_PLAYERS) {
            setGameState(GameState.RUNNING);
        }
    }

    @Override
    public void onPlayerLeave(Player player) {

    }

    @Override
    public void onPlayerChatMessage(Player player, String message) {
        broadcastMessage(player.getName() + ChatColor.GRAY + " » " + ChatColor.RESET + message);
    }

    @Override
    public void cleanUp() {
        gameWorld.unload();
    }

    @Override
    public World getWorld() {
        return gameWorld.getWorld();
    }

    private void checkWin() {
        Set<Player> alive = getPlayers().stream().filter(player -> player.getGameMode() == GameMode.SURVIVAL).collect(Collectors.toSet());
        long aliveLeft = alive.size();

        if (aliveLeft == 1) {
            winner = alive.stream().findFirst().get();
            setGameState(GameState.FINISHED);
        } else {
            broadcastMessage(ChatColor.GOLD + "Only " + aliveLeft + " players remaining");
        }
    }

    private Location getRandomGameWorldSpawnLocation() {
        String worldName = gameWorld.getWorldName();

        switch (worldName) {
            case "tntrun_map_0": {
                double r = ThreadLocalRandom.current().nextDouble(20, 40);
                double theta = ThreadLocalRandom.current().nextDouble(Math.PI * 2);

                double x = Math.cos(theta) * r;
                double y = 76;
                double z = Math.sin(theta) * r;

                return new Location(gameWorld.getWorld(), x, y, z);
            }
        }

        throw new IllegalStateException("getRandomGameWorldSpawnLocation() unimplemented for " + worldName);
    }

    @Override
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        Block blockBelowPlayer = player.getLocation().subtract(0, 1, 0).getBlock();
        Block blockBelowBlockBelowPlayer = player.getLocation().subtract(0, 2, 0).getBlock();

        if (blockBelowPlayer.getType() != Material.AIR && gameState == GameState.RUNNING) {
            blockBelowPlayer.setType(Material.AIR);
            blockBelowBlockBelowPlayer.setType(Material.AIR);
        } else super.onPlayerMoveEvent(e);
    }

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        e.getEntity().spigot().respawn();
        PlayerUtil.resetSpectator(e.getEntity());
        e.getEntity().teleport(getRandomGameWorldSpawnLocation());
        checkWin();
    }
  
}
