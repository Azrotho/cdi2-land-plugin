package fr.citedesiles.landplugin.command;

import fr.citedesiles.landplugin.LandPlugin;
import fr.citedesiles.landplugin.corruption.Corruption;
import fr.citedesiles.landplugin.corruption.CorruptionEntities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CorruptionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCette commande ne peut être exécutée que par un joueur.");
            return true;
        }

        if (!player.isOp()) {
            player.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }

        if (args.length == 0) {
            sendHelp(player);
            return true;
        }

        LandPlugin plugin = LandPlugin.getInstance();

        switch (args[0].toLowerCase()) {
            case "start" -> {
                plugin.getCorruptionManager().createCorruption("test", player.getLocation());
                player.sendMessage("§aCorruption démarrée à votre position !");
            }
            case "stop" -> {
                Corruption corruption = plugin.getCorruptionManager().getCorruption("test");
                if (corruption != null) {
                    corruption.stopCorruption();
                    player.sendMessage("§aArrêt de la corruption en cours...");
                } else {
                    player.sendMessage("§cAucune corruption active trouvée.");
                }
            }
            case "pause" -> {
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /corruption pause <true|false>");
                    return true;
                }
                Corruption corruption = plugin.getCorruptionManager().getCorruption("test");
                if (corruption != null) {
                    boolean pause = Boolean.parseBoolean(args[1]);
                    corruption.setPaused(pause);
                    player.sendMessage("§aCorruption " + (pause ? "mise en pause" : "reprise") + ".");
                } else {
                    player.sendMessage("§cAucune corruption active trouvée.");
                }
            }
            case "bpt", "speed" -> {
                if (args.length < 2) {
                    player.sendMessage("§cUsage: /corruption speed <blocksPerTick>");
                    return true;
                }
                try {
                    int bpt = Integer.parseInt(args[1]);
                    Corruption corruption = plugin.getCorruptionManager().getCorruption("test");
                    if (corruption != null) {
                        corruption.setBlocksPerTick(bpt);
                        player.sendMessage("§aVitesse de la corruption réglée à " + bpt + " blocs par tick.");
                    } else {
                        player.sendMessage("§cAucune corruption active trouvée.");
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage("§cVeuillez entrer un nombre entier valide.");
                }
            }
            case "spawnwarden" -> {
                CorruptionEntities.spawnCorruptedWarden(player.getLocation());
                player.sendMessage("§aWarden corrompu fait apparaître !");
            }
            default -> sendHelp(player);
        }

        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage("§6--- Commandes de Corruption ---");
        player.sendMessage("§e/corruption start §7- Démarrer la corruption");
        player.sendMessage("§e/corruption stop §7- Stopper la corruption");
        player.sendMessage("§e/corruption pause <true|false> §7- Mettre en pause / reprendre");
        player.sendMessage("§e/corruption speed <nb> §7- Modifier les blocs par tick");
        player.sendMessage("§e/corruption spawnwarden §7- Spawner un Warden corrompu");
    }
}
