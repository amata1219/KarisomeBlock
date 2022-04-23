package amata1219.karisome.block;

import static amata1219.karisome.block.extension.ItemStackExtension.*;
import static amata1219.karisome.block.extension.WorldExtension.*;

import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;

public class KarisomeBlockPlacing {

    public static void placeKarisomeBlock(Player placer, ItemStack karisomeBlockItemStackInPlacerHand, Location location) {
        long durationInTicks = KarisomeBlockItem.durationInTicks(karisomeBlockItemStackInPlacerHand);
        ItemStack oneOfKarisomeBlockItemStackInPlacerHand = KarisomeBlockItem.createKarisomeBlockItemStack(1, durationInTicks);

        playSound(location, Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 0.0f);

        boolean isSurvivalMode = placer.getGameMode() == GameMode.SURVIVAL;
        if (isSurvivalMode) decrementAmountOf(karisomeBlockItemStackInPlacerHand);

        Location particleSpawnLocation = location.clone().add(0.5, 0.5, 0.5);
        BukkitTask particleSpawner = runTaskTimerAsynchronously(() -> {
            spawnParticle(Particle.ENCHANTMENT_TABLE, particleSpawnLocation, 6);
            spawnParticle(Particle.CRIT_MAGIC, particleSpawnLocation, 2);
            spawnParticle(Particle.PORTAL, particleSpawnLocation, 1);
        }, 0L, 10L);

        Map<Location, Runnable> scheduledSubroutinesOnKarisomeBlockDestruction = KarisomeBlock.plugin().scheduledSubroutinesOnKarisomeBlockDestruction;
        Runnable subroutineOnKarisomeBlockDestruction = () -> {
            location.getBlock().setType(Material.AIR);

            //playSound(location, Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 2.0f);
            playSound(location, Sound.BLOCK_GLASS_BREAK, 1.0f, 0.7f);

            spawnParticle(Particle.BLOCK_CRACK, particleSpawnLocation, 50, KarisomeBlockItem.GLASS_BLOCK_DATA);

            particleSpawner.cancel();

            if (isSurvivalMode) {
                Item item = placer.getWorld().dropItem(placer.getLocation(), oneOfKarisomeBlockItemStackInPlacerHand);
                item.setPickupDelay(0);
            }
        };
        scheduledSubroutinesOnKarisomeBlockDestruction.put(location, subroutineOnKarisomeBlockDestruction);

        runTaskLater(() -> {
            if (!scheduledSubroutinesOnKarisomeBlockDestruction.containsKey(location)) return;

            subroutineOnKarisomeBlockDestruction.run();
            scheduledSubroutinesOnKarisomeBlockDestruction.remove(location);
        }, durationInTicks);
    }

    private static BukkitTask runTaskTimerAsynchronously(Runnable task, long delayInTicks, long periodInTicks) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(KarisomeBlock.plugin(), task, delayInTicks, periodInTicks);
    }

    private static void runTaskLater(Runnable task, long delayInTicks) {
        Bukkit.getScheduler().runTaskLater(KarisomeBlock.plugin(), task, delayInTicks);
    }

}
