package pl.doleckijakub.mc.minigames;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.util.PlayerUtil;

public class Lobby extends Minigame {

    private static final Lobby instance = new Lobby();

    public static Lobby getInstance() {
        return instance;
    }

    @Override
    public String getGameStateString() {
        return null;
    }

    @Override
    public void teleportPlayer(Player player) {
        player.teleport(getWorld().getSpawnLocation().add(0.5, 0, 0.5));
    }

    @Override
    public void onPlayerJoin(Player player) {
        PlayerUtil.resetAdventure(player);
        player.setAllowFlight(true);
    }

    @Override
    public void onPlayerLeave(Player player) {

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
        if (player.getLocation().subtract(0, 64, 0).length() > 50) {
            player.setFallDistance(0);
            teleportPlayer(player);
        }
    }

}
