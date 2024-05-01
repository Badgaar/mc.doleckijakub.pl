package pl.doleckijakub.mc.common;

import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.exceptions.PlayerNotFoundInAnyMinigameException;
import pl.doleckijakub.mc.exceptions.WorldNotFoundInAnyMinigameException;
import pl.doleckijakub.mc.minigames.Lobby;
import pl.doleckijakub.mc.minigames.Spleef;

import javax.naming.InvalidNameException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class MinigameManager {

    // ID

    private static AtomicInteger ID = new AtomicInteger(0);

    public static int getNextId() {
        return ID.incrementAndGet() % 9999 + 1;
    }

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

    private static final Map<Class<? extends Minigame>, Map<Integer, Minigame>> CURRENT_GAMES = new HashMap<>();

    public static Map<Integer, Minigame> getCurrentGames(String name) throws InvalidNameException {
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

        minigame.teleportPlayer(player);
    }

    public static Minigame getMinigameByWorld(World world) {
        for (Class<? extends Minigame> k1 : CURRENT_GAMES.keySet()) {
            for (int k2 : CURRENT_GAMES.get(k1).keySet()) {
                Minigame minigame = CURRENT_GAMES.get(k1).get(k2);
                if (world.equals(minigame.getWorld())) {
                    return minigame;
                }
            }
        }

        if (Lobby.getInstance().getWorld().equals(world)) return Lobby.getInstance();

        throw new WorldNotFoundInAnyMinigameException(world);
    }

    public static Minigame getMinigameByPlayer(Player player) {
        for (Class<? extends Minigame> k1 : CURRENT_GAMES.keySet()) {
            for (int k2 : CURRENT_GAMES.get(k1).keySet()) {
                Minigame minigame = CURRENT_GAMES.get(k1).get(k2);
                if (minigame.hasPlayer(player)) {
                    return minigame;
                }
            }
        }

//        if (Lobby.getInstance().hasPlayer(player)) return Lobby.getInstance();
//
//        throw new PlayerNotFoundInAnyMinigameException(player);

        return Lobby.getInstance();
    }

}
