package amata1219.karisome.blocks.listener;

import amata1219.karisome.blocks.KarisomeBlockItem;
import amata1219.karisome.blocks.KarisomeBlockPlacing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class KarisomeBlockPlaceListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (KarisomeBlockItem.isKarisomeBlockItem(item)) KarisomeBlockPlacing.placeKarisomeBlock(
                event.getPlayer(), item, event.getBlock().getLocation(), KarisomeBlockItem.durationInTicks(item)
        );
    }
}
