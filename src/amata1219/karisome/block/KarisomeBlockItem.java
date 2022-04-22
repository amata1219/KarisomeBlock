package amata1219.karisome.block;

import static amata1219.karisome.block.extension.ItemStackExtension.*;

import amata1219.karisome.block.itemstack.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class KarisomeBlockItem {

    public static final Material KARISOME_BLOCK_TYPE = Material.GLASS;

    public static ItemStack createKarisomeBlockItemStack(int amount, long durationInTicks) {
        return ItemStackBuilder.builder()
                .type(KARISOME_BLOCK_TYPE)
                .amount(amount)
                .displayName(ChatColor.WHITE + "Karisome Block")
                .lore(ChatColor.GRAY + "Duration: " + durationInTicks + " ticks")
                .persistentDataContainer(container -> container.set(
                        keyForKarisomeBlockDuration(),
                        PersistentDataType.LONG,
                        durationInTicks
                )).build();
    }

    public static boolean isKarisomeBlockItemStack(ItemStack itemStack) {
        return itemStack != null && itemStack.getType() == KARISOME_BLOCK_TYPE && hasPersistentData(itemStack, keyForKarisomeBlockDuration(), PersistentDataType.LONG);
    }

    private static <T, Z> boolean hasPersistentData(ItemStack itemStack, NamespacedKey persistentDataKey, PersistentDataType<T, Z> persistentDataType) {
        return itemStack.hasItemMeta() && itemMetaOf(itemStack).getPersistentDataContainer().has(persistentDataKey, persistentDataType);
    }

    private static NamespacedKey keyForKarisomeBlockDuration() {
        return KarisomeBlock.plugin().keyForKarisomeBlockDuration();
    }

    public static long durationInTicks(ItemStack karisomeBlockItemStack) {
        if (!KarisomeBlockItem.isKarisomeBlockItemStack(karisomeBlockItemStack)) throw new IllegalArgumentException("karisomeBlockItem isn't an item of the karisome block.");

        Long durationInTicks = itemMetaOf(karisomeBlockItemStack)
                .getPersistentDataContainer()
                .get(keyForKarisomeBlockDuration(), PersistentDataType.LONG);
        if (durationInTicks == null) throw new NullPointerException("karisomeBlockItemStack has no duration value.");

        return durationInTicks;
    }

}
