package amata1219.karisome.blocks.extension;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;

public class WorldExtension {
    public static void playSound(Location location, Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    public static void spawnParticle(Particle particle, Location location, int count) {
        location.getWorld().spawnParticle(particle, location, count);
    }

    public static void spawnParticle(Particle particle, Location location, int count, BlockData blockData) {
        location.getWorld().spawnParticle(particle, location, count, blockData);
    }
}
