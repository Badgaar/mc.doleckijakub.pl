package pl.doleckijakub.mc.common;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.server.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.painting.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.event.world.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.hanging.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;


import java.util.HashSet;
import java.util.Set;

public abstract class Minigame {

    private final int id;
    private final Set<Player> players;

    protected Minigame() {
        this.id = MinigameManager.getNextId();
        this.players = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void addPlayer(Player player) {
        players.add(player);
        onPlayerJoin(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        onPlayerLeave(player);
    }

	public boolean hasPlayer(Player player) {
		return players.contains(player);
	}

    public int getPlayerCount() {
        return players.size();
    }

    // minigame interface

    public abstract String getGameStateString();

	public abstract void teleportPlayer(Player player);
    public abstract void onPlayerJoin(Player player);
    public abstract void onPlayerLeave(Player player);

	public abstract World getWorld();

	public void onPluginEvent(PluginEvent e) {}
	public void onServerEvent(ServerEvent e) {}
	public void onServiceRegisterEvent(ServiceRegisterEvent e) {}
	public void onServiceUnregisterEvent(ServiceUnregisterEvent e) {}
	public void onServerListPingEvent(ServerListPingEvent e) {}
	public void onServiceEvent(ServiceEvent e) {}
	public void onPluginDisableEvent(PluginDisableEvent e) {}
	public void onMapInitializeEvent(MapInitializeEvent e) {}
	public void onRemoteServerCommandEvent(RemoteServerCommandEvent e) {}
	public void onServerCommandEvent(ServerCommandEvent e) {}
	public void onPluginEnableEvent(PluginEnableEvent e) {}
	public void onWeatherEvent(WeatherEvent e) {}
	public void onWeatherChangeEvent(WeatherChangeEvent e) {}
	public void onLightningStrikeEvent(LightningStrikeEvent e) {}
	public void onThunderChangeEvent(ThunderChangeEvent e) {}
	public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent e) {}
	public void onVehicleUpdateEvent(VehicleUpdateEvent e) {}
	public void onVehicleDestroyEvent(VehicleDestroyEvent e) {}
	public void onVehicleCollisionEvent(VehicleCollisionEvent e) {}
	public void onVehicleExitEvent(VehicleExitEvent e) {}
	public void onVehicleEnterEvent(VehicleEnterEvent e) {}
	public void onVehicleCreateEvent(VehicleCreateEvent e) {}
	public void onVehicleMoveEvent(VehicleMoveEvent e) {}
	public void onVehicleEvent(VehicleEvent e) {}
	public void onVehicleDamageEvent(VehicleDamageEvent e) {}
	public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent e) {}
	public void onPaintingBreakByEntityEvent(PaintingBreakByEntityEvent e) {}
	public void onPaintingPlaceEvent(PaintingPlaceEvent e) {}
	public void onPaintingEvent(PaintingEvent e) {}
	public void onPaintingBreakEvent(PaintingBreakEvent e) {}
	public void onEnchantItemEvent(EnchantItemEvent e) {}
	public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent e) {}
	public void onChunkLoadEvent(ChunkLoadEvent e) {}
	public void onChunkEvent(ChunkEvent e) {}
	public void onChunkPopulateEvent(ChunkPopulateEvent e) {}
	public void onWorldInitEvent(WorldInitEvent e) {}
	public void onStructureGrowEvent(StructureGrowEvent e) {}
	public void onSpawnChangeEvent(SpawnChangeEvent e) {}
	public void onWorldSaveEvent(WorldSaveEvent e) {}
	public void onChunkUnloadEvent(ChunkUnloadEvent e) {}
	public void onWorldEvent(WorldEvent e) {}
	public void onWorldUnloadEvent(WorldUnloadEvent e) {}
	public void onPortalCreateEvent(PortalCreateEvent e) {}
	public void onWorldLoadEvent(WorldLoadEvent e) {}
	public void onFurnaceExtractEvent(FurnaceExtractEvent e) {}
	public void onPrepareItemCraftEvent(PrepareItemCraftEvent e) {}
	public void onInventoryInteractEvent(InventoryInteractEvent e) {}
	public void onInventoryCloseEvent(InventoryCloseEvent e) {}
	public void onCraftItemEvent(CraftItemEvent e) {}
	public void onInventoryEvent(InventoryEvent e) {}
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent e) {}
	public void onFurnaceSmeltEvent(FurnaceSmeltEvent e) {}
	public void onInventoryCreativeEvent(InventoryCreativeEvent e) {}
	public void onBrewEvent(BrewEvent e) {}
	public void onInventoryOpenEvent(InventoryOpenEvent e) {}
	public void onInventoryPickupItemEvent(InventoryPickupItemEvent e) {}
	public void onInventoryClickEvent(InventoryClickEvent e) {}
	public void onInventoryDragEvent(InventoryDragEvent e) {}
	public void onFurnaceBurnEvent(FurnaceBurnEvent e) {}
	public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent e) {}
	public void onHangingEvent(HangingEvent e) {}
	public void onHangingPlaceEvent(HangingPlaceEvent e) {}
	public void onHangingBreakEvent(HangingBreakEvent e) {}
	public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {}
	public void onEntityCombustEvent(EntityCombustEvent e) {}
	public void onPigZapEvent(PigZapEvent e) {}
	public void onEntityTeleportEvent(EntityTeleportEvent e) {}
	public void onEntityInteractEvent(EntityInteractEvent e) {}
	public void onEntityExplodeEvent(EntityExplodeEvent e) {}
	public void onEntityEvent(EntityEvent e) {}
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {}
	public void onEntityPortalExitEvent(EntityPortalExitEvent e) {}
	public void onProjectileLaunchEvent(ProjectileLaunchEvent e) {}
	public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent e) {}
	public void onCreeperPowerEvent(CreeperPowerEvent e) {}
	public void onSlimeSplitEvent(SlimeSplitEvent e) {}
	public void onEntityDeathEvent(EntityDeathEvent e) {}
	public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {}
	public void onEntityTargetEvent(EntityTargetEvent e) {}
	public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent e) {}
	public void onEntityDamageEvent(EntityDamageEvent e) {}
	public void onEntityBreakDoorEvent(EntityBreakDoorEvent e) {}
	public void onItemSpawnEvent(ItemSpawnEvent e) {}
	public void onExpBottleEvent(ExpBottleEvent e) {}
	public void onEntityUnleashEvent(EntityUnleashEvent e) {}
	public void onEntityCreatePortalEvent(EntityCreatePortalEvent e) {}
	public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent e) {}
	public void onEntityShootBowEvent(EntityShootBowEvent e) {}
	public void onHorseJumpEvent(HorseJumpEvent e) {}
	public void onEntityPortalEnterEvent(EntityPortalEnterEvent e) {}
	public void onPotionSplashEvent(PotionSplashEvent e) {}
	public void onSheepDyeWoolEvent(SheepDyeWoolEvent e) {}
	public void onProjectileHitEvent(ProjectileHitEvent e) {}
	public void onItemDespawnEvent(ItemDespawnEvent e) {}
	public void onPlayerDeathEvent(PlayerDeathEvent e) {}
	public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent e) {}
	public void onEntityRegainHealthEvent(EntityRegainHealthEvent e) {}
	public void onExplosionPrimeEvent(ExplosionPrimeEvent e) {}
	public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent e) {}
	public void onEntityTameEvent(EntityTameEvent e) {}
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {}
	public void onEntityPortalEvent(EntityPortalEvent e) {}
	public void onCreatureSpawnEvent(CreatureSpawnEvent e) {}
	public void onBlockFromToEvent(BlockFromToEvent e) {}
	public void onBlockPlaceEvent(BlockPlaceEvent e) {}
	public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent e) {}
	public void onBlockDamageEvent(BlockDamageEvent e) {}
	public void onBlockBurnEvent(BlockBurnEvent e) {}
	public void onBlockExpEvent(BlockExpEvent e) {}
	public void onLeavesDecayEvent(LeavesDecayEvent e) {}
	public void onBlockSpreadEvent(BlockSpreadEvent e) {}
	public void onBlockDispenseEvent(BlockDispenseEvent e) {}
	public void onBlockPistonRetractEvent(BlockPistonRetractEvent e) {}
	public void onBlockPhysicsEvent(BlockPhysicsEvent e) {}
	public void onBlockFormEvent(BlockFormEvent e) {}
	public void onBlockBreakEvent(BlockBreakEvent e) {}
	public void onBlockRedstoneEvent(BlockRedstoneEvent e) {}
	public void onBlockCanBuildEvent(BlockCanBuildEvent e) {}
	public void onBlockFadeEvent(BlockFadeEvent e) {}
	public void onBlockGrowEvent(BlockGrowEvent e) {}
	public void onNotePlayEvent(NotePlayEvent e) {}
	public void onBlockPistonExtendEvent(BlockPistonExtendEvent e) {}
	public void onSignChangeEvent(SignChangeEvent e) {}
	public void onEntityBlockFormEvent(EntityBlockFormEvent e) {}
	public void onBlockPistonEvent(BlockPistonEvent e) {}
	public void onBlockIgniteEvent(BlockIgniteEvent e) {}
	public void onPlayerQuitEvent(PlayerQuitEvent e) {}
	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent e) {}
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {}
	public void onPlayerInteractEvent(PlayerInteractEvent e) {}
	public void onPlayerTeleportEvent(PlayerTeleportEvent e) {}
	public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e) {}
	public void onPlayerEditBookEvent(PlayerEditBookEvent e) {}
	public void onPlayerPortalEvent(PlayerPortalEvent e) {}
	public void onPlayerBucketFillEvent(PlayerBucketFillEvent e) {}
	public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {}
	public void onPlayerEggThrowEvent(PlayerEggThrowEvent e) {}
	public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent e) {}
	public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent e) {}
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {}
	public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {}
	public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent e) {}
	public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent e) {}
	public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent e) {}
	public void onPlayerJoinEvent(PlayerJoinEvent e) {}
	public void onPlayerVelocityEvent(PlayerVelocityEvent e) {}
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {}
	public void onPlayerBucketEvent(PlayerBucketEvent e) {}
	public void onPlayerItemBreakEvent(PlayerItemBreakEvent e) {}
	public void onPlayerPreLoginEvent(PlayerPreLoginEvent e) {}
	public void onPlayerFishEvent(PlayerFishEvent e) {}
	public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent e) {}
	public void onPlayerMoveEvent(PlayerMoveEvent e) {}
	public void onPlayerKickEvent(PlayerKickEvent e) {}
	public void onPlayerExpChangeEvent(PlayerExpChangeEvent e) {}
	public void onPlayerAnimationEvent(PlayerAnimationEvent e) {}
	public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {}
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent e) {}
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {}
	public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {}
	public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent e) {}
	public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent e) {}
	public void onPlayerInventoryEvent(PlayerInventoryEvent e) {}
	public void onPlayerChatEvent(PlayerChatEvent e) {}
	public void onPlayerLoginEvent(PlayerLoginEvent e) {}
	public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent e) {}
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent e) {}
	public void onPlayerChannelEvent(PlayerChannelEvent e) {}
	public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent e) {}
	public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent e) {}
	public void onPlayerRespawnEvent(PlayerRespawnEvent e) {}
	public void onPlayerShearEntityEvent(PlayerShearEntityEvent e) {}

}
