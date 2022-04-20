package amata1219.karisome.block.extension;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackExtension {
    //This function can only be used in contexts where it is certain to ItemMeta is not null.
    public static ItemMeta itemMetaOf(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) throw new NullPointerException("the item meta is null");
        return itemStack.getItemMeta();
    }

    public static void decrementAmountOf(ItemStack itemStack) {
        int amount = itemStack.getAmount();
        if (amount > 1) itemStack.setAmount(amount - 1);
        else itemStack.setType(Material.AIR);
    }
}
