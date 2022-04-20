package amata1219.karisome.block.listener;

import amata1219.karisome.block.KarisomeBlockItem;
import amata1219.karisome.block.KarisomeBlockPlacing;
import amata1219.karisome.block.KarisomeBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class KarisomeBlockListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (KarisomeBlockItem.isKarisomeBlockItemStack(item)) KarisomeBlockPlacing.placeKarisomeBlock(
                event.getPlayer(), item, event.getBlock().getLocation(), KarisomeBlockItem.durationInTicks(item)
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.TINTED_GLASS) return;

        event.setCancelled(true);
        KarisomeBlock.plugin().tryRunSubroutineOnKarisomeBlockDestruction(block.getLocation());
    }
}
