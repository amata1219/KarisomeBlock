package amata1219.karisome.blocks;

import amata1219.karisome.blocks.command.KarisomeBlocksCommand;
import amata1219.karisome.blocks.listener.KarisomeBlockPlaceListener;
import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class KarisomeBlocks extends JavaPlugin {

    private static KarisomeBlocks instance;

    private NamespacedKey keyForKarisomeBlockDuration;

    @Override
    public void onEnable() {
        instance = this;
        keyForKarisomeBlockDuration = new NamespacedKey(instance, "duration");

        getCommand("karisome").setExecutor(new KarisomeBlocksCommand());

        registerListeners(new KarisomeBlockPlaceListener());
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) getServer().getPluginManager().registerEvents(listener, instance);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(instance);
    }

    public static KarisomeBlocks plugin() {
        return instance;
    }

    public NamespacedKey keyForKarisomeBlockDuration() {
        return keyForKarisomeBlockDuration;
    }

}
