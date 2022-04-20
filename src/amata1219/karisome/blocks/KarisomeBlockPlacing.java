package amata1219.karisome.blocks;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KarisomeBlockPlacing {
    public static void placeKarisomeBlock(Player placer, ItemStack karisomeBlockItemInPlacerHand, Location location, long durationInTicks) {
        ItemStack oneOfKarisomeBlockItemInPlacerHand = KarisomeBlockItem.createKarisomeBlocks(1, durationInTicks);

        placer.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.5f);

        int amount = karisomeBlockItemInPlacerHand.getAmount();
        if (amount > 1) karisomeBlockItemInPlacerHand.setAmount(amount - 1);
        else karisomeBlockItemInPlacerHand.setType(Material.AIR);

        runTaskTimer(() -> location.getWorld().spawnParticle(Particle.SNOWFLAKE, location, 20, 1, 1, 1), 0L, 10L);

        runTaskLater(() -> {
            location.getBlock().breakNaturally();
            placer.getInventory().addItem(oneOfKarisomeBlockItemInPlacerHand);
        }, durationInTicks);
    }

    private static void runTaskLater(Runnable task, long delayInTicks) {
        Bukkit.getScheduler().runTaskLater(KarisomeBlocks.plugin(), task, delayInTicks);
    }

    private static void runTaskTimer(Runnable task, long delayInTicks, long periodInTicks) {
        Bukkit.getScheduler().runTaskTimer(KarisomeBlocks.plugin(), task, delayInTicks, periodInTicks);
    }

}
