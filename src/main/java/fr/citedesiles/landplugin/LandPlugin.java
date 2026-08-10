package fr.citedesiles.landplugin;

import fr.citedesiles.coreplugin.CoreCDI;
import fr.citedesiles.landplugin.command.CorruptionCommand;
import fr.citedesiles.landplugin.config.PluginConfig;
import fr.citedesiles.landplugin.corruption.CorruptionManager;
import fr.citedesiles.landplugin.listener.ChatListener;
import fr.citedesiles.landplugin.listener.CorruptionListener;
import fr.citedesiles.landplugin.listener.PlayerJoinListener;
import fr.citedesiles.landplugin.runnable.CorruptionRunnable;
import fr.citedesiles.landplugin.scoreboard.SidebarManager;
import fr.citedesiles.landplugin.stats.StatBuffer;
import fr.citedesiles.landplugin.stats.StatsListener;
import fr.citedesiles.landplugin.util.TeamDisplayManager;

import org.bukkit.plugin.java.JavaPlugin;

public class LandPlugin extends JavaPlugin {

    private static LandPlugin instance;
    private PluginConfig config;
    private CoreCDI api;
    private SidebarManager sidebarManager;
    private CorruptionManager corruptionManager;
    private StatBuffer statBuffer;

    @Override
    public void onEnable() {
        instance = this;
        corruptionManager = new CorruptionManager();

        // Charger la configuration
        config = new PluginConfig(this);

        String apiUrl = config.getApiUrl();
        String apiToken = config.getApiToken();

        if (!apiToken.isEmpty()) {
            // Initialiser le client API
            api = new CoreCDI(apiUrl, apiToken);

            // Tester la connexion à l'API
            try {
                if (api.ping()) {
                    getLogger().info("Connecté à l'API CDI2 : " + apiUrl);
                    TeamDisplayManager.orderTeamsInScoreboard(api, this);
                    sidebarManager = new SidebarManager(api, this, config.getSidebarTitle());
                    sidebarManager.startFooterRotation();
                    sidebarManager.startDataRefresh();

                    // Initialiser le tracking de stats
                    statBuffer = new StatBuffer(this, api);
                    statBuffer.start();
                    getServer().getPluginManager().registerEvents(new StatsListener(statBuffer), this);
                }
            } catch (CoreCDI.ApiException e) {
                getLogger().warning("Impossible de contacter l'API CDI2 : " + e.getMessage());
            }
        } else {
            getLogger().warning("Le token API est vide ! Configure 'api.token' dans config.yml");
        }

        // Enregistrer les listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(api, config), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new CorruptionListener(), this);

        // Enregistrer le runnable de corruption
        new CorruptionRunnable().runTaskTimer(this, 0, 0);

        // Enregistrer les commandes
        if (getCommand("corruption") != null) {
            getCommand("corruption").setExecutor(new CorruptionCommand());
        }

        getLogger().info("LandPlugin activé !");
    }

    @Override
    public void onDisable() {
        if (statBuffer != null) {
            statBuffer.stop();
        }
        getLogger().info("LandPlugin désactivé !");
    }

    public static LandPlugin getInstance() {
        return instance;
    }

    public PluginConfig getPluginConfig() {
        return config;
    }

    public CoreCDI getApi() {
        return api;
    }

    public SidebarManager getSidebarManager() {
        return sidebarManager;
    }

    public CorruptionManager getCorruptionManager() {
        return corruptionManager;
    }

    public StatBuffer getStatBuffer() {
        return statBuffer;
    }
}