package amata1219.karisome.blocks;

import amata1219.karisome.blocks.extension.ItemStackExtension;
import amata1219.karisome.blocks.itemstack.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class KarisomeBlockItem {

    public static ItemStack createKarisomeBlocks(int amount, long durationInTicks) {
        return ItemStackBuilder.builder()
                .type(Material.GLASS)
                .amount(amount)
                .displayName(ChatColor.WHITE + "Karisome Block")
                .lore(ChatColor.GRAY + "Duration: " + durationInTicks + "s")
                .persistentDataContainer(container -> container.set(
                        keyForKarisomeBlockDuration(),
                        PersistentDataType.LONG,
                        durationInTicks
                )).build();
    }

    public static boolean isKarisomeBlockItem(ItemStack item) {
        return hasPersistentData(item, keyForKarisomeBlockDuration(), PersistentDataType.LONG);
    }

    private static <T, Z> boolean hasPersistentData(ItemStack item, NamespacedKey persistentDataKey, PersistentDataType<T, Z> persistentDataType) {
        return item.hasItemMeta() && ItemStackExtension.itemMeta(item).getPersistentDataContainer().has(persistentDataKey, persistentDataType);
    }

    private static NamespacedKey keyForKarisomeBlockDuration() {
        return KarisomeBlocks.plugin().keyForKarisomeBlockDuration();
    }

    public static long durationInTicks(ItemStack karisomeBlockItem) {
        if (!KarisomeBlockItem.isKarisomeBlockItem(karisomeBlockItem)) throw new IllegalArgumentException("karisomeBlockItem isn't an item of the karisome block.");

        Long durationInTicks = ItemStackExtension.itemMeta(karisomeBlockItem)
                .getPersistentDataContainer()
                .get(keyForKarisomeBlockDuration(), PersistentDataType.LONG);
        if (durationInTicks == null) throw new NullPointerException("karisomeBlockItem has no duration value.");

        return durationInTicks;
    }

}
