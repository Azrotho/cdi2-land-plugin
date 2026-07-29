package fr.citedesiles.landplugin.listener;

import fr.citedesiles.landplugin.LandPlugin;
import fr.citedesiles.landplugin.corruption.Corruption;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class CorruptionListener implements Listener {
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getTarget() == null || event.getTarget() instanceof Player) return;
        LandPlugin plugin = LandPlugin.getInstance();
        if (plugin == null || plugin.getCorruptionManager() == null) return;
        Corruption corruption = plugin.getCorruptionManager().getCorruption();
        if (corruption != null && corruption.isInCorruption(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;
        LandPlugin plugin = LandPlugin.getInstance();
        if (plugin == null || plugin.getCorruptionManager() == null) return;
        Corruption corruption = plugin.getCorruptionManager().getCorruption();
        if (corruption != null && corruption.isInCorruption(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }
    }
}
