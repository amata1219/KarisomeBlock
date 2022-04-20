package amata1219.karisome.blocks.itemstack;

import amata1219.karisome.blocks.extension.ItemStackExtension;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ItemStackBuilder {

    private Material type;
    private int amount;
    private String displayName;
    private List<String> lore;
    private Consumer<PersistentDataContainer> persistentDataContainer;

    public static ItemStackBuilder builder() {
        return new ItemStackBuilder();
    }

    public ItemStackBuilder type(Material type) {
        this.type = type;
        return this;
    }

    public ItemStackBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStackBuilder displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemStackBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemStackBuilder persistentDataContainer(Consumer<PersistentDataContainer> persistentDataContainer) {
        this.persistentDataContainer = persistentDataContainer;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(type, amount);

        ItemMeta meta = ItemStackExtension.itemMeta(item);
        meta.setDisplayName(displayName);
        meta.setLore(lore);

        PersistentDataContainer container = meta.getPersistentDataContainer();
        persistentDataContainer.accept(container);

        item.setItemMeta(meta);
        return item;
    }

}
