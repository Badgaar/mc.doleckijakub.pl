package pl.doleckijakub.mc.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
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

    }

    @Override
    public void onPlayerLeave(Player player) {

    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld("lobby");
    }

    @Override
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        PlayerUtil.resetAdventure(e.getPlayer());
        e.getPlayer().setAllowFlight(true);
    }

    @Override
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        e.setCancelled(true);
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
