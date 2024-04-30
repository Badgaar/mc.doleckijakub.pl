package pl.doleckijakub.mc.common;

import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class Minigame implements Listener {

    private final UUID id;

    protected Minigame() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    // minigame interface

    public abstract String getGameStateString();

}
