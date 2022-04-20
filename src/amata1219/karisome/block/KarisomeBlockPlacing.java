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
    public static void placeKarisomeBlock(Player placer, ItemStack karisomeBlockItemStackInPlacerHand, Location location, long durationInTicks) {
        ItemStack oneOfKarisomeBlockItemStackInPlacerHand = KarisomeBlockItem.createKarisomeBlockItemStack(1, durationInTicks);

        placer.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.65f);

        boolean isSurvivalMode = placer.getGameMode() == GameMode.SURVIVAL;
        if (isSurvivalMode) decrementAmountOf(karisomeBlockItemStackInPlacerHand);

        Location particleSpawnLocation = location.clone().add(0.5, 0.5, 0.5);
        BukkitTask particleSpawner = runTaskTimerAsynchronously(() -> {
            spawnParticle(Particle.ENCHANTMENT_TABLE, particleSpawnLocation, 10);
            spawnParticle(Particle.CRIT_MAGIC, particleSpawnLocation, 4);
        }, 0L, 10L);

        Map<Location, Runnable> scheduledSubroutinesOnKarisomeBlockDestruction = KarisomeBlock.plugin().scheduledSubroutinesOnKarisomeBlockDestruction;

        Runnable subroutineOnKarisomeBlockDestruction = () -> {
            location.getBlock().setType(Material.AIR);
            playSound(location, Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
            spawnParticle(Particle.BLOCK_CRACK, particleSpawnLocation, 10, Material.TINTED_GLASS.createBlockData());

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
