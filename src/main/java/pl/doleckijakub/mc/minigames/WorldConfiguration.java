package pl.doleckijakub.mc.minigames;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;
import pl.doleckijakub.mc.util.EnglishUtil;
import pl.doleckijakub.mc.util.PlayerUtil;

import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

    private static final String CHOOSER_TITLE = "Choose one from below";
    private CompletableFuture<ItemStack> chooserFuture = null;

    public CompletableFuture<ItemStack> chooseItem(Player player, ItemStack[] items) {
        int inventorySize = (int) Math.ceil(items.length / 9.0) * 9;

        Inventory chooserInventory = Bukkit.createInventory(null, inventorySize, CHOOSER_TITLE);
        chooserInventory.clear();
        for (ItemStack material : items) {
            chooserInventory.addItem(new ItemStack(material));
        }

        player.openInventory(chooserInventory);

        chooserFuture = new CompletableFuture<>();

        return chooserFuture;
    }

    private void chooseTeam(Player player, Consumer<Bedwars.TeamColor> callback) {
        List<ItemStack> wools = Arrays.stream(Bedwars.TeamColor.values()).map(Bedwars.TeamColor::getWoolItemStack).collect(Collectors.toList());
        ItemStack[] items = new ItemStack[wools.size()];
        for (int i = 0; i < wools.size(); i++) items[i] = wools.get(i);

        chooseItem(player, items).thenAccept(itemStack -> {
            Bedwars.TeamColor teamColor = Bedwars.TeamColor.fromWoolItem(itemStack);
            callback.accept(teamColor);
        });
    }

    @Override
    public void onInventoryClickEvent(InventoryClickEvent e) {
        InventoryView view = e.getView();
        if (view.getTitle().equals(CHOOSER_TITLE)) {
            chooserFuture.complete(e.getCurrentItem());

            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        }
    }

    @Override
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        if (!chooserFuture.isDone()) chooserFuture.cancel(false);
    }

    @Override
    public void onBlockBreakEvent(BlockBreakEvent e) {
        e.setCancelled(true);

        Player player = e.getPlayer();
        Material type = e.getBlock().getType();

        switch (player.getItemInHand().getType()) {
            case BED: {
                if (type != Material.BED_BLOCK) { player.sendMessage(ChatColor.RED + "Not a bed"); return; }
                chooseTeam(player, teamColor -> {
                    String path = "team." + teamColor.toString().toLowerCase() + ".bed";
                    gameWorld.setConfigLocation(path, e.getBlock().getLocation());
                    player.sendMessage(teamColor.getChatColor() + EnglishUtil.firstUpper(teamColor.toString()) + "'s " + ChatColor.RESET + "bed is now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
                });
            } break;
            case STICK: {
                chooseTeam(player, teamColor -> {
                    String path = "team." + teamColor.toString().toLowerCase() + ".shop";
                    gameWorld.setConfigLocation(path, e.getBlock().getLocation());

                    ArmorStand armorStand = (ArmorStand) getWorld().spawnEntity(e.getBlock().getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
                    armorStand.setHelmet(new ItemStack(Material.IRON_HELMET));

                    player.sendMessage(teamColor.getChatColor() + EnglishUtil.firstUpper(teamColor.toString()) + "'s " + ChatColor.RESET + "shop is now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
                });
            } break;
            case BLAZE_ROD: {
                chooseTeam(player, teamColor -> {
                    String path = "team." + teamColor.toString().toLowerCase() + ".upgrades";
                    gameWorld.setConfigLocation(path, e.getBlock().getLocation());

                    ArmorStand armorStand = (ArmorStand) getWorld().spawnEntity(e.getBlock().getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
                    armorStand.setHelmet(new ItemStack(Material.GOLD_HELMET));

                    player.sendMessage(teamColor.getChatColor() + EnglishUtil.firstUpper(teamColor.toString()) + "'s " + ChatColor.RESET + "upgrades are now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
                });
            } break;
            case IRON_INGOT: {
                chooseTeam(player, teamColor -> {
                    String path = "team." + teamColor.toString().toLowerCase() + ".summoner";
                    gameWorld.setConfigLocation(path, e.getBlock().getLocation());

                    ArmorStand armorStand = (ArmorStand) getWorld().spawnEntity(e.getBlock().getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
                    armorStand.setHelmet(new ItemStack(Material.LEATHER_HELMET));

                    player.sendMessage(teamColor.getChatColor() + EnglishUtil.firstUpper(teamColor.toString()) + "'s " + ChatColor.RESET + "summoner is now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
                });
            } break;
            case DIAMOND: {
                for (int i = 0; i < 4; i++) {
                    String path = "summoner.diamond." + i;
                    Location configLocation = gameWorld.getConfigLocation(path);
                    if (configLocation == null) {
                        gameWorld.setConfigLocation(path, e.getBlock().getLocation());

                        ArmorStand armorStand = (ArmorStand) getWorld().spawnEntity(e.getBlock().getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
                        armorStand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));

                        player.sendMessage("Diamond summoner nr. " + i + " is now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
                        return;
                    }
                }

                player.sendMessage(ChatColor.RED + "All diamond summoners already set up");
            } break;
            case EMERALD: {
                for (int i = 0; i < 4; i++) {
                    String path = "summoner.emerald." + i;
                    Location configLocation = gameWorld.getConfigLocation(path);
                    if (configLocation == null) {
                        gameWorld.setConfigLocation(path, e.getBlock().getLocation());

                        ArmorStand armorStand = (ArmorStand) getWorld().spawnEntity(e.getBlock().getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
                        armorStand.setHelmet(new ItemStack(Material.LEATHER_HELMET));
                        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) armorStand.getHelmet().getItemMeta();
                        leatherArmorMeta.setColor(Color.fromRGB(0, 255, 0));
                        armorStand.getHelmet().setItemMeta(leatherArmorMeta);

                        player.sendMessage("Emerald summoner nr. " + i + " is now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
                        return;
                    }
                }

                player.sendMessage(ChatColor.RED + "All emerald summoners already set up");
            } break;
            case ENDER_PEARL: {
                String path = "specialist";
                gameWorld.setConfigLocation(path, e.getBlock().getLocation());

                ArmorStand armorStand = (ArmorStand) getWorld().spawnEntity(e.getBlock().getLocation().add(0, 1, 0), EntityType.ARMOR_STAND);
                armorStand.setHelmet(new ItemStack(Material.LEATHER_HELMET));
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) armorStand.getHelmet().getItemMeta();
                leatherArmorMeta.setColor(Color.fromRGB(255, 0, 255));
                armorStand.getHelmet().setItemMeta(leatherArmorMeta);

                player.sendMessage("The specialist is now at " + ChatColor.GOLD + gameWorld.getConfigLocation(path).toVector().toString());
            } break;
        }
    }
}
