package pl.doleckijakub.mc.exceptions;

import org.bukkit.World;

public class WorldNotFoundInAnyMinigameException extends RuntimeException {

    public WorldNotFoundInAnyMinigameException(World world) {
        super("World " + world.getName() + " not found in any minigame");
    }

}
