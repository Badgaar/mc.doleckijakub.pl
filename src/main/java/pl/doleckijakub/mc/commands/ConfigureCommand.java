package pl.doleckijakub.mc.commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.doleckijakub.mc.common.CommandInfo;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.common.MinigameManager;
import pl.doleckijakub.mc.common.PluginCommand;
import pl.doleckijakub.mc.minigames.WorldConfiguration;
import pl.doleckijakub.mc.util.ItemUtils;

@CommandInfo(name = "configure", permissions = "op")
public class ConfigureCommand extends PluginCommand {

    public static final class Bedwars {
        public static final ItemStack bedSetter = new ItemStack(Material.BED);
        public static final ItemStack shopSetter = new ItemStack(Material.STICK);
        public static final ItemStack upgradeSetter = new ItemStack(Material.BLAZE_ROD);

        public static final ItemStack teamSummonerSetter = new ItemStack(Material.IRON_INGOT);
        public static final ItemStack diamondSummonerSetter = new ItemStack(Material.DIAMOND);
        public static final ItemStack emeraldSummonerSetter = new ItemStack(Material.EMERALD);

        public static final ItemStack specialistSetter = new ItemStack(Material.ENDER_PEARL);

        static {
            ItemUtils.setDisplayName(bedSetter, "Bed location setter");
            ItemUtils.setDisplayName(shopSetter, "Team shop location setter");
            ItemUtils.setDisplayName(upgradeSetter, "Team upgrade location setter");
            ItemUtils.setDisplayName(teamSummonerSetter, "Team summoner location setter");
            ItemUtils.setDisplayName(diamondSummonerSetter, "Diamond summoner location setter");
            ItemUtils.setDisplayName(emeraldSummonerSetter, "Emerald summoner location setter");
            ItemUtils.setDisplayName(specialistSetter, "Specialist location setter");
        }
    }

    @Override
    public void execute(Player player, String[] args) {
        switch (args[0]) {
            case "join": {
                MinigameManager.playerJoinOwnMinigame(player, new WorldConfiguration(args[1]));
            } break;
            case "bedwars": {
                player.getInventory().clear();
                player.getInventory().addItem(
                        Bedwars.bedSetter,
                        Bedwars.shopSetter,
                        Bedwars.upgradeSetter,
                        Bedwars.teamSummonerSetter,
                        Bedwars.diamondSummonerSetter,
                        Bedwars.emeraldSummonerSetter,
                        Bedwars.specialistSetter
                );
            } break;
            case "__test": {
                Minigame minigame = MinigameManager.getMinigameByPlayer(player);
                if (!(minigame instanceof WorldConfiguration)) throw new RuntimeException("Not in configuration");
                WorldConfiguration worldConfiguration = (WorldConfiguration) minigame;

                // set
                worldConfiguration.setConfigString(args[1], args[2]);
                player.sendMessage(worldConfiguration.getWorldName() + "." + args[1] + " is now " + worldConfiguration.getConfigString(args[1]));

                // get
                player.sendMessage(worldConfiguration.getConfigAsString());
            } break;
        }

    }

}
