package fr.citedesiles.landplugin;

import fr.citedesiles.coreplugin.CoreCDI;
import fr.citedesiles.landplugin.config.PluginConfig;
import fr.citedesiles.landplugin.listener.ChatListener;
import fr.citedesiles.landplugin.listener.PlayerJoinListener;

import org.bukkit.plugin.java.JavaPlugin;

public class LandPlugin extends JavaPlugin {

    private PluginConfig config;
    private CoreCDI api;

    @Override
    public void onEnable() {
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

        getLogger().info("LandPlugin activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("LandPlugin désactivé !");
    }

    public PluginConfig getPluginConfig() {
        return config;
    }

    public CoreCDI getApi() {
        return api;
    }
}