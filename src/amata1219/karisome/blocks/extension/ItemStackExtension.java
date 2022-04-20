package amata1219.karisome.blocks.extension;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackExtension {
    //This function can only be used in contexts where it is certain to ItemMeta is not null.
    public static ItemMeta itemMeta(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) throw new NullPointerException("the item meta is null");
        return itemStack.getItemMeta();
    }
}
