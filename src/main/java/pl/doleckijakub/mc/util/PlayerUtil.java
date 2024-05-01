package pl.doleckijakub.mc.util;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

public final class PlayerUtil {

    public static void reset(Player player) {
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setSaturation(20);
        player.setExhaustion(0);
        player.setFallDistance(0);
        player.setExp(0);
        player.setLevel(0);
        player.setRemainingAir(player.getMaximumAir());

        player.setFlying(false);
        player.setAllowFlight(false);
        player.setCustomNameVisible(false);

        player.setBedSpawnLocation(null);
        player.setResourcePack(null);

        player.setVelocity(player.getVelocity().zero());
        player.setDisplayName(player.getName());

        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.getScoreboard().getObjectives().forEach(Objective::unregister);
        player.getInventory().clear();
        player.closeInventory();
    }

    public static void resetAdventure(Player player) {
        reset(player);
        player.setGameMode(GameMode.ADVENTURE);
    }

    public static void resetSurvival(Player player) {
        reset(player);
        player.setGameMode(GameMode.SURVIVAL);
    }

}
