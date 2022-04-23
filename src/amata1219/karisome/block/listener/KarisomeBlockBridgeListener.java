package amata1219.karisome.block.listener;

import static org.bukkit.Material.*;

import amata1219.karisome.block.KarisomeBlock;
import amata1219.karisome.block.KarisomeBlockItem;
import amata1219.karisome.block.KarisomeBlockPlacing;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class KarisomeBlockBridgeListener implements Listener {

    private static final Set<Material> REPLACEABLE_TYPES = Set.of(
            AIR,
            CAVE_AIR,
            GRASS,
            TALL_GRASS,
            FERN,
            LARGE_FERN,
            VINE,
            DEAD_BUSH,
            SEAGRASS,
            TALL_SEAGRASS,
            NETHER_SPROUTS,
            CRIMSON_ROOTS,
            WARPED_ROOTS
    );

    @EventHandler(ignoreCancelled = true)
    public void onSwap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (!player.isSneaking() || !KarisomeBlockItem.isKarisomeBlockItemStack(itemInMainHand)) return;

        event.setCancelled(true);

        Location location = player.getLocation().add(0, 0.75, 0);
        MagmaCube guide = spawnMagmaCubeGuideAt(new Location(location.getWorld(), 0, 0, 0));
        AtomicInteger placementCounter = new AtomicInteger();
        runTimerSynchronously(task -> {
            if (itemInMainHand.getAmount() == 0) {
                guide.remove();
                task.cancel();
                return;
            }

            Location newLocation = location.clone().add(player.getLocation().getDirection());
            if (location.getBlockX() == newLocation.getBlockX() && location.getBlockY() == newLocation.getBlockY() && location.getBlockZ() == newLocation.getBlockZ()) {
                newLocation.add(player.getLocation().getDirection());
            }

            location.setX(newLocation.getX());
            location.setY(newLocation.getY());
            location.setZ(newLocation.getZ());

            Block block = location.getBlock();
            if (!REPLACEABLE_TYPES.contains(block.getType())) {
                guide.remove();
                task.cancel();
                return;
            }

            guide.teleport(centerOf(location));

            block.setType(KarisomeBlockItem.GLASS_TYPE);
            KarisomeBlockPlacing.placeKarisomeBlock(player, itemInMainHand, location.clone());

            if (!(-64 < location.getBlockY() && location.getBlockY() < 319) || placementCounter.incrementAndGet() == 64) {
                guide.remove();
                task.cancel();
            }
        }, 0, 1);
    }

    private static MagmaCube spawnMagmaCubeGuideAt(Location location) {
        MagmaCube guide = location.getWorld().spawn(location, MagmaCube.class);
        guide.setSize(2);
        guide.setInvulnerable(true);
        guide.setInvisible(true);
        guide.setAI(false);
        guide.setGravity(false);
        guide.setSilent(true);
        guide.setGlowing(true);
        return guide;
    }

    private static Location centerOf(Location location) {
        return new Location(location.getWorld(), location.getBlockX() + 0.5, location.getBlockY(), location.getBlockZ() + 0.5, 0.0f, 0.0f);
    }

    private static void runTimerSynchronously(Consumer<BukkitTask> task, long delayInTicks, long periodInTicks) {
        Bukkit.getScheduler().runTaskTimer(KarisomeBlock.plugin(), task, delayInTicks, periodInTicks);
    }

}
