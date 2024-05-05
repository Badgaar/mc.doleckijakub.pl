package pl.doleckijakub.mc.minigames;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitTask;
import pl.doleckijakub.mc.Plugin;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.common.MinigameManager;
import pl.doleckijakub.mc.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Spleef extends Minigame {

    private static final int MIN_PLAYERS = 2;
    private static final int COUNTDOWN_LENGTH = 10;

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

    private final GameWorld lobbyWorld;
    private final GameWorld gameWorld;

    private int countdown;
    private BukkitTask timer;

    private Player winner;

    public Spleef() {
        super();
        this.gameState = GameState.LOBBY;
        this.lobbyWorld = new GameWorld("spleef_lobby");
        this.gameWorld  = new GameWorld("spleef_map_0");
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    private static final ItemStack shovel;
    private static final ItemStack bow;
    private static final ItemStack arrow;

    static {
        shovel = new ItemStack(Material.DIAMOND_SPADE);
        shovel.addEnchantment(Enchantment.DIG_SPEED, 5);

        bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        arrow = new ItemStack(Material.ARROW);
    }

    private Location getRandomGameWorldSpawnLocation() {
        String worldName = gameWorld.getWorldName();

        switch (worldName) {
            case "spleef_map_0": {
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

    private void setGameState(GameState newGameState) {
        switch (gameState) {
            case LOBBY: {
                if (newGameState == GameState.RUNNING) {
                    for (Player player : lobbyWorld.getWorld().getPlayers()) {
                        player.teleport(getRandomGameWorldSpawnLocation());

                        player.setGameMode(GameMode.SURVIVAL);

                        player.getInventory().clear();
                        player.getInventory().setItem(9, arrow);
                        player.getInventory().setItem(0, shovel);
                        player.getInventory().setItem(1, bow);
                    }
                } else {
                    throw new IllegalStateException("unreachable");
                }

                gameState = newGameState;
            } break;
            case RUNNING: {
                if (newGameState == GameState.FINISHED) {
                    broadcastMessage(ChatColor.GREEN + "Player " + winner.getName() + " won the game");

                    countdown = 40;
                    timer = Bukkit.getScheduler().runTaskTimer(Plugin.getInstance(), () -> {
                        Firework firework = (Firework) getWorld().spawnEntity(winner.getLocation(), EntityType.FIREWORK);
                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                            fireworkMeta.setPower(2);
                            fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(
                                    ThreadLocalRandom.current().nextInt(255),
                                    ThreadLocalRandom.current().nextInt(255),
                                    ThreadLocalRandom.current().nextInt(255)
                            )).flicker(true).build());

                        if (countdown-- == 0) {
                            teleportAllPlayersToLobby();
                            timer.cancel();
                        }
                    }, 20, 5);
                } else {
                    throw new IllegalStateException("unreachable");
                }

                gameState = newGameState;
            } break;
            case FINISHED: {
                throw new IllegalStateException("unreachable");
            }
        }
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

    @Override
    public void teleportPlayer(Player player) {
        switch (gameState) {
            case LOBBY: {
                player.teleport(lobbyWorld.getWorld().getSpawnLocation());
            } break;
            case RUNNING:
            case FINISHED: {
                player.teleport(getRandomGameWorldSpawnLocation());
            } break;
        }
    }

    @Override
    public void onPlayerJoin(Player player) {
        switch (gameState) {
            case LOBBY: {
                PlayerUtil.resetAdventure(player);
                if (getPlayerCount() == MIN_PLAYERS) {
                    countdown = COUNTDOWN_LENGTH;
                    timer = Bukkit.getScheduler().runTaskTimer(Plugin.getInstance(), () -> {
                        broadcastSound(Sound.CLICK, 1, 1);
                        broadcastMessage((countdown > 5 ? ChatColor.GOLD : ChatColor.RED) + "Starting in " + countdown);

                        if (countdown-- == 0) {
                            setGameState(GameState.RUNNING);
                            timer.cancel();
                        }
                    }, 20, 20);
                }
            } break;
            case RUNNING: {
                PlayerUtil.resetSpectator(player);
            } break;
            case FINISHED: {
                PlayerUtil.resetSpectator(player);
            } break;
        }
    }

    @Override
    public void onPlayerLeave(Player player) {
        switch (gameState) {
            case LOBBY: {
                if (timer != null && getPlayerCount() == MIN_PLAYERS - 1) {
                    timer.cancel();
                    timer = null;
                }
            } break;
            case RUNNING: {
                checkWin();
            } break;
            case FINISHED: {

            } break;
        }
    }

    @Override
    public void cleanUp() {
        lobbyWorld.unload();
        gameWorld.unload();
    }

    @Override
    public World getWorld() {
        switch (gameState) {
            case LOBBY: return lobbyWorld.getWorld();
            case RUNNING:
            case FINISHED: return gameWorld.getWorld();
        }

        return null;
    }

    @Override
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onBlockBreakEvent(BlockBreakEvent e) {
        switch (gameState) {
            case LOBBY:
            case FINISHED: {
                e.setCancelled(true);
            } break;
            case RUNNING: {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                broadcastSound(e.getBlock().getLocation(), Sound.DIG_SNOW, 1, 1);
                e.getPlayer().getInventory().addItem(new ItemStack(Material.SNOW_BALL));
            } break;
        }
    }

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        e.getEntity().spigot().respawn();
        PlayerUtil.resetSpectator(e.getEntity());
        e.getEntity().teleport(getRandomGameWorldSpawnLocation());
        checkWin();
    }

    @Override
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (gameState != GameState.RUNNING) {
            e.setCancelled(true);
        }
    }

    @Override
    public void onProjectileHitEvent(ProjectileHitEvent e) {
//        e.getEntity()
    }

}
