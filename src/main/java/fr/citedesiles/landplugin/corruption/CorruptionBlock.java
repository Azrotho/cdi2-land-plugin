package fr.citedesiles.landplugin.corruption;

import org.bukkit.Location;
import org.bukkit.Material;

public class CorruptionBlock {
    private Material ancientBlock;
    private Location location;

    public CorruptionBlock(Material ancientBlock, Location location) {
        this.ancientBlock = ancientBlock;
        this.location = location;
    }

    public Material getAncientBlock() {
        return ancientBlock;
    }

    public void setAncientBlock(Material ancientBlock) {
        this.ancientBlock = ancientBlock;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
