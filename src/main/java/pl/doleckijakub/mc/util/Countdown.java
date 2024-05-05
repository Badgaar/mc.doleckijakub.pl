package pl.doleckijakub.mc.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import pl.doleckijakub.mc.Plugin;

public abstract class Countdown {

    private int ticksLeft;
    private BukkitTask bukkitTask;

    public Countdown(int ticksTotal, int tickLength) {
        this.ticksLeft = ticksTotal;
        this.bukkitTask = Bukkit.getScheduler().runTaskTimer(Plugin.getInstance(), () -> {
            tick(ticksLeft);

            if (--ticksLeft == 0) {
                onFinished();
                cancel();
            }
        }, 0, tickLength);
    }

    public void cancel() {
        bukkitTask.cancel();
    }

    public abstract void tick(int ticksLeft);
    public abstract void onFinished();

}
