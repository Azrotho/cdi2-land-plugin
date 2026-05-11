package fr.citedesiles.landplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class LandPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("LandPlugin a été activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("LandPlugin a été désactivé !");
    }
}