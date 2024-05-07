package pl.doleckijakub.mc.common;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.doleckijakub.mc.exceptions.PlayerNotFoundInAnyMinigameException;
import pl.doleckijakub.mc.exceptions.WorldNotFoundInAnyMinigameException;
import pl.doleckijakub.mc.minigames.*;
import pl.doleckijakub.mc.util.ANSI;

import javax.naming.InvalidNameException;
import javax.persistence.Lob;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
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
    private static final Map<String, Class<? extends Minigame>> MINIGAME_NAMES_HIDDEN = new HashMap<>();

    static {
        MINIGAME_NAMES.put("spleef", Spleef.class);
        MINIGAME_NAMES.put("tntrun", TNTRun.class);
        MINIGAME_NAMES.put("bed_solo",  Bedwars.Solo.class);
//        MINIGAME_NAMES.put("bed_duo",   Bedwars.Duo.class);
//        MINIGAME_NAMES.put("bed_teams", Bedwars.Teams.class);

        MINIGAME_NAMES_HIDDEN.put("lobby", Lobby.class);
        MINIGAME_NAMES_HIDDEN.put("config", WorldConfiguration.class);
    }

    public static String getMinigameName(Class<? extends Minigame> minigameClass) {
        return MINIGAME_NAMES.entrySet().stream().filter(entry -> entry.getValue().equals(minigameClass)).map(Map.Entry::getKey).findFirst().orElseGet(() ->
            MINIGAME_NAMES_HIDDEN.entrySet().stream().filter(entry -> entry.getValue().equals(minigameClass)).map(Map.Entry::getKey).findFirst().get()
        );
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

    public static void onPlayerLeaveServer(Player player) {
        getMinigameByPlayer(player).removePlayer(player);
    }

    private static void playerSetMinigame(Player player, Minigame minigame) {
        getMinigameByPlayer(player).removePlayer(player);
        minigame.teleportPlayer(player);
        minigame.addPlayer(player);
    }

    public static void playerJoinNewGame(Player player, String name) throws InvalidNameException {
        Class<? extends Minigame> minigameClass = getMinigameClassByName(name);
        Minigame minigame = createMinigame(minigameClass);

        if (!CURRENT_GAMES.containsKey(minigameClass)) CURRENT_GAMES.put(minigameClass, new HashMap<>());
        CURRENT_GAMES.get(minigameClass).put(minigame.getId(), minigame);

        playerSetMinigame(player, minigame);
    }

    public static void playerJoinOwnMinigame(Player player, Minigame minigame) {
        Class<? extends Minigame> minigameClass = minigame.getClass();

        if (!CURRENT_GAMES.containsKey(minigameClass)) CURRENT_GAMES.put(minigameClass, new HashMap<>());
        CURRENT_GAMES.get(minigameClass).put(minigame.getId(), minigame);

        playerSetMinigame(player, minigame);
    }

    public static void playerJoinGame(Player player, String name, int id) throws InvalidNameException, InvalidKeyException {
        Class<? extends Minigame> minigameClass = getMinigameClassByName(name);

        try {
            Minigame minigame = CURRENT_GAMES.get(minigameClass).get(id);

            playerSetMinigame(player, minigame);
        } catch (NullPointerException e) {
            throw new InvalidKeyException();
        }
    }

    public static void playerJoinLobby(Player player) {
        playerSetMinigame(player, Lobby.getInstance());
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

        return Lobby.getInstance();
    }

    public static void stopMinigame(Class<? extends Minigame> minigameClass, int id) {
        CURRENT_GAMES.get(minigameClass).get(id).log(ANSI.BLUE + "Stopping minigame " + getMinigameName(minigameClass) + "_" + String.format("%04d", id) + ANSI.RESET);
        CURRENT_GAMES.get(minigameClass).remove(id);
    }

}
