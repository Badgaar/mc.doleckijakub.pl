package pl.doleckijakub.mc.common;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.minigames.Spleef;

import javax.naming.InvalidNameException;
import java.lang.reflect.InvocationTargetException;
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

    // CLASS -> MAP<ID, MINIGAME>

    private static final Map<Class<? extends Minigame>, Map<UUID, Minigame>> CURRENT_GAMES = new HashMap<>();

    public static Map<UUID, Minigame> getCurrentGames(String name) throws InvalidNameException {
        return CURRENT_GAMES.getOrDefault(getMinigameClassByName(name), new HashMap<>());
    }

    private static Minigame createMinigame(Class<? extends Minigame> minigameClass) {
        try {
            return minigameClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playerJoinNewGame(Player player, String name) throws InvalidNameException {
        Class<? extends Minigame> minigameClass = getMinigameClassByName(name);
        Minigame minigame = createMinigame(minigameClass);

        if (!CURRENT_GAMES.containsKey(minigameClass)) CURRENT_GAMES.put(minigameClass, new HashMap<>());
        CURRENT_GAMES.get(minigameClass).put(minigame.getId(), minigame);

        minigame.addPlayer(player);
    }

}
