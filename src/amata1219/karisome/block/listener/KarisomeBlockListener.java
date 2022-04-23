package amata1219.karisome.block.listener;

import amata1219.karisome.block.KarisomeBlockItem;
import amata1219.karisome.block.KarisomeBlockPlacing;
import amata1219.karisome.block.KarisomeBlock;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class KarisomeBlockListener implements Listener {

    private final KarisomeBlock plugin = KarisomeBlock.plugin();

    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        ItemStack itemInMainHand = event.getItemInHand();
        if (KarisomeBlockItem.isKarisomeBlockItemStack(itemInMainHand)) KarisomeBlockPlacing.placeKarisomeBlock(event.getPlayer(), itemInMainHand, event.getBlock().getLocation());
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == KarisomeBlockItem.GLASS_TYPE && plugin.tryRunSubroutineOnKarisomeBlockDestruction(block.getLocation())) event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPistonExtend(BlockPistonExtendEvent event) {
        for (Block block : event.getBlocks()) {
            if (plugin.doesKarisomeBlockExistAt(block.getLocation())) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPistonExtend(BlockPistonRetractEvent event) {
        for (Block block : event.getBlocks()) {
            if (plugin.doesKarisomeBlockExistAt(block.getLocation())) {
                event.setCancelled(true);
                break;
            }
        }
    }

}
