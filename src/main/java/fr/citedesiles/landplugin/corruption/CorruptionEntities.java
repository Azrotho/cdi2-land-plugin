package fr.citedesiles.landplugin.corruption;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Warden;
import org.bukkit.entity.Zombie;

public class CorruptionEntities {
    public static void spawnCorruptedZombie(Location location) {
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        zombie.setCustomName("§1§lZombie corrompu");
        zombie.setCustomNameVisible(true);
        if (zombie.getAttribute(Attribute.MAX_HEALTH) != null) {
            zombie.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        }
        if (zombie.getAttribute(Attribute.SCALE) != null) {
            zombie.getAttribute(Attribute.SCALE).setBaseValue(1.5);
        }
        zombie.setHealth(40);
    }

    public static void spawnCorruptedSkeleton(Location location) {
        Skeleton skeleton = location.getWorld().spawn(location, Skeleton.class);
        skeleton.setCustomName("§1§lSquelette corrompu");
        skeleton.setCustomNameVisible(true);
        if (skeleton.getAttribute(Attribute.MAX_HEALTH) != null) {
            skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        }
        if (skeleton.getAttribute(Attribute.SCALE) != null) {
            skeleton.getAttribute(Attribute.SCALE).setBaseValue(1.5);
        }
        skeleton.setHealth(40);
    }

    public static void spawnCorruptedWarden(Location location) {
        Warden warden = location.getWorld().spawn(location, Warden.class);
        warden.setCustomName("§1§lWarden corrompu");
        warden.setCustomNameVisible(true);
        if (warden.getAttribute(Attribute.MAX_HEALTH) != null) {
            warden.getAttribute(Attribute.MAX_HEALTH).setBaseValue(100);
        }
        if (warden.getAttribute(Attribute.SCALE) != null) {
            warden.getAttribute(Attribute.SCALE).setBaseValue(1.5);
        }
        if (warden.getAttribute(Attribute.ATTACK_DAMAGE) != null) {
            warden.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4);
        }
        warden.setHealth(100);
    }
}
