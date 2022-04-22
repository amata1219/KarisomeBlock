package amata1219.karisome.block;

import amata1219.karisome.block.command.KarisomeBlocksCommand;
import amata1219.karisome.block.listener.KarisomeBlockBridgeListener;
import amata1219.karisome.block.listener.KarisomeBlockListener;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class KarisomeBlock extends JavaPlugin {

    private static KarisomeBlock instance;

    private NamespacedKey keyForKarisomeBlockDuration;
    Map<Location, Runnable> scheduledSubroutinesOnKarisomeBlockDestruction = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        keyForKarisomeBlockDuration = new NamespacedKey(instance, "duration");

        getCommand("karisome").setExecutor(new KarisomeBlocksCommand());

        registerListeners(
                new KarisomeBlockListener(),
                new KarisomeBlockBridgeListener()
        );
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) getServer().getPluginManager().registerEvents(listener, instance);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(instance);

        scheduledSubroutinesOnKarisomeBlockDestruction.values().forEach(Runnable::run);
        scheduledSubroutinesOnKarisomeBlockDestruction.clear();
    }

    public static KarisomeBlock plugin() {
        return instance;
    }

    public NamespacedKey keyForKarisomeBlockDuration() {
        return keyForKarisomeBlockDuration;
    }

    public boolean doesKarisomeBlockExistAt(Location location) {
        return scheduledSubroutinesOnKarisomeBlockDestruction.containsKey(location);
    }

    public boolean tryRunSubroutineOnKarisomeBlockDestruction(Location locationMayHaveKarisomeBlock) {
        if (doesKarisomeBlockExistAt(locationMayHaveKarisomeBlock)) {
            scheduledSubroutinesOnKarisomeBlockDestruction.remove(locationMayHaveKarisomeBlock).run();
            return true;
        }
        return false;
    }

}
