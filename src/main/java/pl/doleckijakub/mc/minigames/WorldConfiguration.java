package pl.doleckijakub.mc.minigames;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.util.PlayerUtil;

public class WorldConfiguration extends Minigame {

    private final String worldName;
    private final GameWorld gameWorld;

    public WorldConfiguration(String worldName) {
        this.gameWorld = new GameWorld(this.worldName = worldName);
    }

    @Override
    public String getGameStateString() {
        throw new IllegalStateException("unreachable");
    }

    @Override
    public void teleportPlayer(Player player) {
        player.teleport(gameWorld.getWorld().getSpawnLocation());
    }

    @Override
    public void onPlayerJoin(Player player) {
        log("onPlayerJoin");
        PlayerUtil.resetCreative(player);
    }

    @Override
    public void onPlayerLeave(Player player) {
        log("onPlayerLeave");
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

    @Override
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Location location = player.getLocation();

        player.sendMessage("Bro, how tf did u die in creative? xd");

        player.spigot().respawn();
        player.teleport(location);
    }

    public String getConfigAsString() {
        return gameWorld.getConfigAsString();
    }

    public String getConfigString(String path) {
        return gameWorld.getConfigString(path);
    }

    public void setConfigString(String path, String value) {
        gameWorld.setConfigString(path, value);
    }

    public String getWorldName() {
        return worldName;
    }

}
