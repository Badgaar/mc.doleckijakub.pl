package pl.doleckijakub.mc.common;

import pl.doleckijakub.mc.minigames.Spleef;

import javax.naming.InvalidNameException;
import java.util.*;

public final class MinigameManager {

    // NAME -> CLASS

    private static final Map<String, Class<? extends Minigame>> MINIGAME_NAMES = new HashMap<>();

    static {
        MINIGAME_NAMES.put("spleef", Spleef.class);
    }

    private static Class<? extends Minigame> getMinigameClassByName(String name) throws InvalidNameException {
        if (!MINIGAME_NAMES.containsKey(name)) throw new InvalidNameException();
        return MINIGAME_NAMES.get(name);
    }

    public static Set<String> getMinigameNames() {
        return MINIGAME_NAMES.keySet();
    }

    // CLASS -> SET<MINIGAMES>

    private static final Map<Class<? extends Minigame>, Map<UUID, Minigame>> CURRENT_GAMES = new HashMap<>();

    public static Map<UUID, Minigame> getCurrentGames(String name) throws InvalidNameException {
        return CURRENT_GAMES.getOrDefault(getMinigameClassByName(name), new HashMap<>());
    }

}
