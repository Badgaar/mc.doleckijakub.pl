package pl.doleckijakub.mc.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.junit.Test;
import pl.doleckijakub.mc.common.GameWorld;
import pl.doleckijakub.mc.common.Minigame;

import static org.bukkit.Bukkit.getServer;

public class KingOfTheSheep extends Minigame {


    public enum GameState {
        LOBBY,
        RUNNING,
        FINISHED;

        @Override
        public String toString() {
            switch (this) {
                case LOBBY:
                    return ChatColor.GREEN + "Lobby";
                case RUNNING:
                    return ChatColor.GOLD + "Running";
                case FINISHED:
                    return ChatColor.RED + "Finished";
            }

            return null;
        }
    }

    private GameState gameState;

    private GameWorld lobbyWorld;
    private GameWorld gameWorld;

    public KingOfTheSheep() {
        super();
        this.gameState = GameState.LOBBY;
        this.lobbyWorld = new GameWorld("king_of_the_sheep_lobby");
    }

    @Override
    public String getGameStateString() {
        return gameState.toString();
    }

    @Override
    public void teleportPlayer(Player player) {
        switch (gameState) {
            case LOBBY: {
                while (this.lobbyWorld == null) ;
                player.teleport(lobbyWorld.getWorld().getSpawnLocation());
            }
            break;
        }
    }

    @Override
    public void onPlayerJoin(Player player) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Player " + player.getName() + " joined King Of The Sheep game " + getId());
    }

    @Override
    public void onPlayerLeave(Player player) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Player " + player.getName() + " left King Of The Sheep game " + getId());
    }

    @Override
    public World getWorld() {
        switch (gameState) {
            case LOBBY:
                return lobbyWorld.getWorld();
            default:
                throw new RuntimeException("unimplemented");
        }
    }

    //1s=20tic
    public int secondsToStart = 10;
    String stringedSecondsToStart = "" + secondsToStart;

    //Centered countdown
    public void sendTimerMessage(Player player, int secondsToStart) {
        int messageLength = stringedSecondsToStart.length();
        int centerMessage = 154 / 2;
        int messageStart = centerMessage - (messageLength / 2);
        String spaces = "";
        for (int i = 0; i < messageStart; i++) {
            spaces += " ";
        }
        player.sendMessage(spaces + secondsToStart);
    }

    //Start countdown
    private void startCountdown() {
        Bukkit.getScheduler().runTaskTimer(KingOfTheSheep, () -> {
            if (secondsToStart <= 0) {
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendTimerMessage(player, secondsToStart);
            }
            secondsToStart--;
        }, 0L, 20L);
    }

    //Create 4 teams blu,RED (SPY IN THE BASE) ,grin(ch),yeloł
    public enum Team{,
        BLUE,
        RED,
        GREEN,
        YELLOW;
    }

    public int[] teamPlayerCount = new int[Team.values().length]

    //Assign players (3perTeam)
    public void onPlayerJoin(Player player){
        player.Team != null;
        if(getPlayerTeam(player) == Team.NONE) {
            assignToTeam(player);
        }

    }
    public void assignToTeam(){
        for (Team team : Team.values()){
            if (team == Team.NONE){
                continue;
            }

            if (teamPlayerCount[team.ordinal()] == 0){
                setPlayerTeam(player, team);
            }
        }
    }

    public void setPlayerTeam(Player player, Team team){
        if ()
    }
    //Set team spawn (If team owns Sheep make them spawn on top of the mountain 10 sec delay)
    //Create sheep object C: White
    //Set sheep to a static place on map
    //Give kits to players
    //Later to add: villager shops, way to obtain barter items,
    //Sheep timer 2 minutes = win

}
