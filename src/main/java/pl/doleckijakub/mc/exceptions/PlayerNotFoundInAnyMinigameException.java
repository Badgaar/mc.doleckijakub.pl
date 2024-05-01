package pl.doleckijakub.mc.exceptions;

import org.bukkit.entity.Player;

public class PlayerNotFoundInAnyMinigameException extends RuntimeException {

    public PlayerNotFoundInAnyMinigameException(Player player) {
        super("Player " + player.getName() + " [" + player.getUniqueId() + "] not found in any minigame");
    }

}
