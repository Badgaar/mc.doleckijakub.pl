package pl.doleckijakub.mc.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.*;
import pl.doleckijakub.mc.common.MinigameManager;
import pl.doleckijakub.mc.minigames.Lobby;
import pl.doleckijakub.mc.util.ANSI;

public class MinigameEventHandler implements Listener {

//    @EventHandler
//    public void onPluginEvent(PluginEvent e) {
//
//    }

//    @EventHandler
//    public void onServerEvent(ServerEvent e) {
//
//    }

//    @EventHandler
//    public void onServiceRegisterEvent(ServiceRegisterEvent e) {
//
//    }

//    @EventHandler
//    public void onServiceUnregisterEvent(ServiceUnregisterEvent e) {
//
//    }

//    @EventHandler
//    public void onServerListPingEvent(ServerListPingEvent e) {
//
//    }

//    @EventHandler
//    public void onServiceEvent(ServiceEvent e) {
//
//    }

//    @EventHandler
//    public void onPluginDisableEvent(PluginDisableEvent e) {
//
//    }

//    @EventHandler
//    public void onMapInitializeEvent(MapInitializeEvent e) {
//
//    }

//    @EventHandler
//    public void onRemoteServerCommandEvent(RemoteServerCommandEvent e) {
//
//    }

//    @EventHandler
//    public void onServerCommandEvent(ServerCommandEvent e) {
//
//    }

//    @EventHandler
//    public void onPluginEnableEvent(PluginEnableEvent e) {
//
//    }

//    @EventHandler
//    public void onWeatherEvent(WeatherEvent e) {
//
//    }

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent e) {
        MinigameManager.getMinigameByWorld(e.getWorld()).onWeatherChangeEvent(e);
    }

//    @EventHandler
//    public void onLightningStrikeEvent(LightningStrikeEvent e) {
//
//    }

//    @EventHandler
//    public void onThunderChangeEvent(ThunderChangeEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleUpdateEvent(VehicleUpdateEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleDestroyEvent(VehicleDestroyEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleCollisionEvent(VehicleCollisionEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleExitEvent(VehicleExitEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleEnterEvent(VehicleEnterEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleCreateEvent(VehicleCreateEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleMoveEvent(VehicleMoveEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleEvent(VehicleEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleDamageEvent(VehicleDamageEvent e) {
//
//    }

//    @EventHandler
//    public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent e) {
//
//    }

//    @EventHandler
//    public void onPaintingBreakByEntityEvent(PaintingBreakByEntityEvent e) {
//
//    }

//    @EventHandler
//    public void onPaintingPlaceEvent(PaintingPlaceEvent e) {
//
//    }

//    @EventHandler
//    public void onPaintingEvent(PaintingEvent e) {
//
//    }

//    @EventHandler
//    public void onPaintingBreakEvent(PaintingBreakEvent e) {
//
//    }

//    @EventHandler
//    public void onEnchantItemEvent(EnchantItemEvent e) {
//
//    }

//    @EventHandler
//    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent e) {
//
//    }

//    @EventHandler
//    public void onChunkLoadEvent(ChunkLoadEvent e) {
//
//    }

//    @EventHandler
//    public void onChunkEvent(ChunkEvent e) {
//
//    }

//    @EventHandler
//    public void onChunkPopulateEvent(ChunkPopulateEvent e) {
//
//    }

//    @EventHandler
//    public void onWorldInitEvent(WorldInitEvent e) {
//
//    }

//    @EventHandler
//    public void onStructureGrowEvent(StructureGrowEvent e) {
//
//    }

//    @EventHandler
//    public void onSpawnChangeEvent(SpawnChangeEvent e) {
//
//    }

//    @EventHandler
//    public void onWorldSaveEvent(WorldSaveEvent e) {
//
//    }

//    @EventHandler
//    public void onChunkUnloadEvent(ChunkUnloadEvent e) {
//
//    }

//    @EventHandler
//    public void onWorldEvent(WorldEvent e) {
//
//    }

//    @EventHandler
//    public void onWorldUnloadEvent(WorldUnloadEvent e) {
//
//    }

//    @EventHandler
//    public void onPortalCreateEvent(PortalCreateEvent e) {
//
//    }

//    @EventHandler
//    public void onWorldLoadEvent(WorldLoadEvent e) {
//
//    }

//    @EventHandler
//    public void onFurnaceExtractEvent(FurnaceExtractEvent e) {
//
//    }

//    @EventHandler
//    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryInteractEvent(InventoryInteractEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryCloseEvent(InventoryCloseEvent e) {
//
//    }

//    @EventHandler
//    public void onCraftItemEvent(CraftItemEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryEvent(InventoryEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryMoveItemEvent(InventoryMoveItemEvent e) {
//
//    }

//    @EventHandler
//    public void onFurnaceSmeltEvent(FurnaceSmeltEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryCreativeEvent(InventoryCreativeEvent e) {
//
//    }

//    @EventHandler
//    public void onBrewEvent(BrewEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryOpenEvent(InventoryOpenEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryPickupItemEvent(InventoryPickupItemEvent e) {
//
//    }

//    @EventHandler
//    public void onInventoryClickEvent(InventoryClickEvent e) {
//        MinigameManager.getMinigameByPlayer((Player) e.getWhoClicked()).onInventoryClickEvent(e);
//    }

//    @EventHandler
//    public void onInventoryDragEvent(InventoryDragEvent e) {
//        MinigameManager.getMinigameByPlayer((Player) e.getWhoClicked()).onInventoryDragEvent(e);
//    }

//    @EventHandler
//    public void onFurnaceBurnEvent(FurnaceBurnEvent e) {
//
//    }

//    @EventHandler
//    public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent e) {
//
//    }

//    @EventHandler
//    public void onHangingEvent(HangingEvent e) {
//
//    }

//    @EventHandler
//    public void onHangingPlaceEvent(HangingPlaceEvent e) {
//
//    }

//    @EventHandler
//    public void onHangingBreakEvent(HangingBreakEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityCombustEvent(EntityCombustEvent e) {
//
//    }

//    @EventHandler
//    public void onPigZapEvent(PigZapEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityTeleportEvent(EntityTeleportEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityInteractEvent(EntityInteractEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityExplodeEvent(EntityExplodeEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityEvent(EntityEvent e) {
//
//    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onFoodLevelChangeEvent(e);
    }

//    @EventHandler
//    public void onEntityPortalExitEvent(EntityPortalExitEvent e) {
//
//    }

//    @EventHandler
//    public void onProjectileLaunchEvent(ProjectileLaunchEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent e) {
//
//    }

//    @EventHandler
//    public void onCreeperPowerEvent(CreeperPowerEvent e) {
//
//    }

//    @EventHandler
//    public void onSlimeSplitEvent(SlimeSplitEvent e) {
//
//    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityDeathEvent(e);
    }

//    @EventHandler
//    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
//
//    }

    @EventHandler
    public void onEntityTargetEvent(EntityTargetEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityTargetEvent(e);
    }

//    @EventHandler
//    public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent e) {
//
//    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityDamageEvent(e);
    }

//    @EventHandler
//    public void onEntityBreakDoorEvent(EntityBreakDoorEvent e) {
//
//    }

    @EventHandler
    public void onItemSpawnEvent(ItemSpawnEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onItemSpawnEvent(e);
    }

//    @EventHandler
//    public void onExpBottleEvent(ExpBottleEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityUnleashEvent(EntityUnleashEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityCreatePortalEvent(EntityCreatePortalEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent e) {
//
//    }

    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityShootBowEvent(e);
    }

//    @EventHandler
//    public void onHorseJumpEvent(HorseJumpEvent e) {
//
//    }

//    @EventHandler
//    public void onEntityPortalEnterEvent(EntityPortalEnterEvent e) {
//
//    }

    @EventHandler
    public void onPotionSplashEvent(PotionSplashEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onPotionSplashEvent(e);
    }

    @EventHandler
    public void onSheepDyeWoolEvent(SheepDyeWoolEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onSheepDyeWoolEvent(e);
    }

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onProjectileHitEvent(e);
    }

    @EventHandler
    public void onItemDespawnEvent(ItemDespawnEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onItemDespawnEvent(e);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onPlayerDeathEvent(e);
    }

//    @EventHandler
//    public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent e) {
//
//    }

    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityRegainHealthEvent(e);
    }

    @EventHandler
    public void onExplosionPrimeEvent(ExplosionPrimeEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onExplosionPrimeEvent(e);
    }

    @EventHandler
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityCombustByEntityEvent(e);
    }

    @EventHandler
    public void onEntityTameEvent(EntityTameEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityTameEvent(e);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onEntityDamageByEntityEvent(e);
    }

//    @EventHandler
//    public void onEntityPortalEvent(EntityPortalEvent e) {
//
//    }

    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
        MinigameManager.getMinigameByWorld(e.getEntity().getWorld()).onCreatureSpawnEvent(e);
    }

//    @EventHandler
//    public void onBlockFromToEvent(BlockFromToEvent e) {
//
//    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockPlaceEvent(e);
    }

//    @EventHandler
//    public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent e) {
//
//    }

    @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockDamageEvent(e);
    }

    @EventHandler
    public void onBlockBurnEvent(BlockBurnEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockBurnEvent(e);
    }

//    @EventHandler
//    public void onBlockExpEvent(BlockExpEvent e) {
//
//    }

    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onLeavesDecayEvent(e);
    }

    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockSpreadEvent(e);
    }

//    @EventHandler
//    public void onBlockDispenseEvent(BlockDispenseEvent e) {
//
//    }

//    @EventHandler
//    public void onBlockPistonRetractEvent(BlockPistonRetractEvent e) {
//
//    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockPhysicsEvent(e);
    }

    @EventHandler
    public void onBlockFormEvent(BlockFormEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockFormEvent(e);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockBreakEvent(e);
    }

//    @EventHandler
//    public void onBlockRedstoneEvent(BlockRedstoneEvent e) {
//
//    }

//    @EventHandler
//    public void onBlockCanBuildEvent(BlockCanBuildEvent e) {
//
//    }

    @EventHandler
    public void onBlockFadeEvent(BlockFadeEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockFadeEvent(e);
    }

    @EventHandler
    public void onBlockGrowEvent(BlockGrowEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockGrowEvent(e);
    }

//    @EventHandler
//    public void onNotePlayEvent(NotePlayEvent e) {
//
//    }

    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockPistonExtendEvent(e);
    }

    @EventHandler
    public void onSignChangeEvent(SignChangeEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onSignChangeEvent(e);
    }

    @EventHandler
    public void onEntityBlockFormEvent(EntityBlockFormEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onEntityBlockFormEvent(e);
    }

    @EventHandler
    public void onBlockIgniteEvent(BlockIgniteEvent e) {
        MinigameManager.getMinigameByWorld(e.getBlock().getWorld()).onBlockIgniteEvent(e);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).removePlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerToggleFlightEvent(e);
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerInteractEntityEvent(e);
    }

//    @EventHandler
//    public void onPlayerInteractEvent(PlayerInteractEvent e) {
//        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerInteractEvent(e);
//    }

    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent e) {
        switch (e.getCause()) {
            case SPECTATE:
            case ENDER_PEARL:
            case PLUGIN: {

            } break;
            case NETHER_PORTAL:
            case END_PORTAL:
            case COMMAND: {
                e.getPlayer().sendMessage(ChatColor.RED + "Teleporting using " + e.getCause().toString() + " is disabled for now. Sorry.");
                e.setCancelled(true);
            } break;
            case UNKNOWN: {
                Bukkit.getLogger().info(ANSI.RED + "Player " + e.getPlayer() + " teleported by cause: UNKNOWN" + ANSI.RESET);
            } break;
        }
//        Bukkit.getLogger().info(ANSI.PURPLE + "onPlayerTeleportEvent " + e + ANSI.RESET);
//
//        World from = e.getFrom().getWorld();
//        World to = e.getTo().getWorld();
//
//        if (from.equals(to)) return;
//
//        MinigameManager.getMinigameByWorld(from).removePlayer(e.getPlayer());
//        MinigameManager.getMinigameByWorld(to).addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerItemConsumeEvent(e);
    }

    @EventHandler
    public void onPlayerEditBookEvent(PlayerEditBookEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerEditBookEvent(e);
    }

//    @EventHandler
//    public void onPlayerPortalEvent(PlayerPortalEvent e) {
//
//    }

    @EventHandler
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerBucketFillEvent(e);
    }

//    @EventHandler
//    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
//
//    }

    @EventHandler
    public void onPlayerEggThrowEvent(PlayerEggThrowEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerEggThrowEvent(e);
    }

//    @EventHandler
//    public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent e) {
//
//    }

    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerToggleSneakEvent(e);
    }

//    @EventHandler
//    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
//
//    }

    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        MinigameManager.onPlayerLeaveServer(e.getPlayer());
    }

//    @EventHandler
//    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e) {
//
//    }

    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerLevelChangeEvent(e);
    }

    @EventHandler
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerAchievementAwardedEvent(e);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        MinigameManager.playerJoinLobby(e.getPlayer());
    }

//    @EventHandler
//    public void onPlayerVelocityEvent(PlayerVelocityEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerBucketEvent(PlayerBucketEvent e) {
//        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerBucketEvent(e);
//    }

    @EventHandler
    public void onPlayerItemBreakEvent(PlayerItemBreakEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerItemBreakEvent(e);
    }

//    @EventHandler
//    public void onPlayerPreLoginEvent(PlayerPreLoginEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerFishEvent(PlayerFishEvent e) {
//
//    }

    @EventHandler
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerToggleSprintEvent(e);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerMoveEvent(e);
    }

//    @EventHandler
//    public void onPlayerKickEvent(PlayerKickEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerExpChangeEvent(PlayerExpChangeEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerAnimationEvent(PlayerAnimationEvent e) {
//
//    }

    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerBucketEmptyEvent(e);
    }

    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerItemHeldEvent(e);
    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerDropItemEvent(e);
    }

    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerBedEnterEvent(e);
    }

    @EventHandler
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerChatTabCompleteEvent(e);
    }

//    @EventHandler
//    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerInventoryEvent(PlayerInventoryEvent e) {
//        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerInventoryEvent(e);
//    }

//    @EventHandler
//    public void onPlayerChatEvent(PlayerChatEvent e) {
//        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerChatEvent(e);
//    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerLoginEvent(e);
    }

//    @EventHandler
//    public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent e) {
//
//    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerPickupItemEvent(e);
    }

//    @EventHandler
//    public void onPlayerChannelEvent(PlayerChannelEvent e) {
//
//    }

//    @EventHandler
//    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent e) {
//
//    }

    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerGameModeChangeEvent(e);
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        MinigameManager.getMinigameByPlayer(e.getPlayer()).onPlayerRespawnEvent(e);
    }

//    @EventHandler
//    public void onPlayerShearEntityEvent(PlayerShearEntityEvent e) {
//
//    }

}
