package fr.citedesiles.landplugin.runnable;

import fr.citedesiles.landplugin.LandPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CorruptionRunnable extends BukkitRunnable {
    @Override
    public void run() {
        LandPlugin plugin = LandPlugin.getInstance();
        if (plugin != null && plugin.getCorruptionManager() != null) {
            plugin.getCorruptionManager().tick();
        }
    }
}
