package pl.doleckijakub.mc.common;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class Minigame implements Listener {

    private final UUID id;
    private final Set<Player> players;

    protected Minigame() {
        this.id = UUID.randomUUID();
        this.players = new HashSet<>();
    }

    public UUID getId() {
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

    public int getPlayerCount() {
        return players.size();
    }

    // minigame interface

    public abstract String getGameStateString();

    public abstract void onPlayerJoin(Player player);
    public abstract void onPlayerLeave(Player player);

}
