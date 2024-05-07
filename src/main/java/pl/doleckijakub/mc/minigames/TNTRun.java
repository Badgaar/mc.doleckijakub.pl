package pl.doleckijakub.mc.minigames;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.util.Countdown;
import pl.doleckijakub.mc.util.PlayerUtil;
import pl.doleckijakub.mc.util.Countdown;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TNTRun extends Minigame{

    private static final int MIN_PLAYERS = 2;
    private static final int COUNTDOWN_LENGTH = 10;
    private Countdown countdown;

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
        switch (gameState) {
            case WAITING: {
                PlayerUtil.resetAdventure(player);
                if (getPlayerCount() == MIN_PLAYERS) {
                    countdown = new Countdown(COUNTDOWN_LENGTH, 10) {

                        @Override
                        public void tick(int ticksLeft) {
                            broadcastSound(Sound.ENDERDRAGON_GROWL, 2, 3);
                            ChatColor titleColor = (ticksLeft > 5 ? ChatColor.GOLD : ChatColor.RED);
                            player.sendTitle( "", titleColor + "Starting in " + ticksLeft);
                        }

                        @Override
                        public void onFinished() {
                            setGameState(GameState.RUNNING);
                        }

                    };
                }
            } break;
            case RUNNING:
            case FINISHED: {
                PlayerUtil.resetSpectator(player);
            } break;
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
        Set<Player> alive = getPlayers().stream().filter(player -> player.getGameMode() == GameMode.ADVENTURE).collect(Collectors.toSet());
        long aliveLeft = alive.size();

        if (aliveLeft == 1) {
            winner = alive.stream().findFirst().get();
            setGameState(GameState.FINISHED);
        } else {
            broadcastMessage(ChatColor.GOLD + "Only " + aliveLeft + " players remaining");
        }
    }

    private Location getSpawn() {
        String worldName = gameWorld.getWorldName();

        switch (worldName) {
            case "tntrun_map_0": {
                switch (gameState) {
                    case WAITING: {
                        double x = 1;
                        double y = 85;
                        double z = 0;

                        return new Location(gameWorld.getWorld(), x, y, z);
                    }
                    case RUNNING:
                    case FINISHED:
                } break;
            }
        }

        throw new IllegalStateException("getSpawn() unimplemented for " + worldName);
    }

    @Override
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        switch (gameState) {
            case WAITING:
            case FINISHED: e.setCancelled(true);
            case RUNNING: {
            Player player = e.getPlayer();

            Block blockBelowPlayer = player.getLocation().subtract(0, 1, 0).getBlock();
            Block blockBelowBlockBelowPlayer = player.getLocation().subtract(0, 2, 0).getBlock();

            if (blockBelowPlayer.getType() != Material.AIR && gameState == GameState.RUNNING) {
                blockBelowPlayer.setType(Material.AIR);
                blockBelowBlockBelowPlayer.setType(Material.AIR);
            } else super.onPlayerMoveEvent(e);
            } break;
        }
    }

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        e.getEntity().spigot().respawn();
        PlayerUtil.resetSpectator(e.getEntity());
        e.getEntity().teleport(getSpawn());
        checkWin();
    }
  
}
