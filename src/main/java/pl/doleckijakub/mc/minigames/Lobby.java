package pl.doleckijakub.mc.minigames;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.util.PlayerUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Lobby extends Minigame {

    private static final Lobby instance = new Lobby();

    public static Lobby getInstance() {
        return instance;
    }

    @Override
    public String getGameStateString() {
        throw new IllegalStateException("unreachable");
    }

    @Override
    public void teleportPlayer(Player player) {
        player.teleport(getWorld().getSpawnLocation().add(0.5, 0, 0.5));
    }

    @Override
    public void onPlayerJoin(Player player) {
        PlayerUtil.resetAdventure(player);
    }

    @Override
    public void onPlayerLeave(Player player) {

    }

    private String colorizedPlayerName(Player player) {
        String name = player.getName();

        switch (name) {
            case "qubix00n":     return ChatColor.DARK_RED + name;
            case "SimpleBadger": return ChatColor.GOLD + name;
            default:             return name;
        }
    }

    @Override
    public void onPlayerChatMessage(Player player, String message) {
        broadcastMessage(colorizedPlayerName(player) + ChatColor.GRAY + " » " + ChatColor.RESET + message);
    }

    @Override
    public void cleanUp() {
        throw new IllegalStateException();
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld("lobby");
    }

    @Override
    public void onWeatherChangeEvent(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onEntityExplodeEvent(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        e.getEntity().spigot().respawn();
    }

    @Override
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getLocation().subtract(0, 64, 0).length() > 50 || player.getLocation().getY() < 60.5) {
            player.setFallDistance(0);
            teleportPlayer(player);
        }
    }

    @Override
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

    @Override
    public void onBlockFadeEvent(BlockFadeEvent e) {
        e.setCancelled(true);
    }
}
