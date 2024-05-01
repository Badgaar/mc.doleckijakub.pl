package pl.doleckijakub.mc.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import pl.doleckijakub.mc.common.Minigame;

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
        player.teleport(getWorld().getSpawnLocation());
    }

    @Override
    public void onPlayerJoin(Player player) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Player " + player.getName() + " joined lobby " + getId());
    }

    @Override
    public void onPlayerLeave(Player player) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Player " + player.getName() + " left lobby " + getId());
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld("lobby");
    }

    @Override
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        e.getPlayer().setAllowFlight(true);
    }

    @Override
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode().equals(GameMode.ADVENTURE)) {
            player.setVelocity(player.getLocation().getDirection().multiply(5));
            e.setCancelled(true);
        }
    }

    @Override
    public void onProjectileHitEvent(ProjectileHitEvent e) {
        if (e.getEntity().getVelocity().lengthSquared() > 0.1) {
            e.getEntity().setVelocity(e.getEntity().getVelocity().multiply(-0.9));
        }
    }
}
