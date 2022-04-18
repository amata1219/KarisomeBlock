package amata1219.karisome.blocks;

import org.bukkit.plugin.java.JavaPlugin;

public class KarisomeBlocks extends JavaPlugin {

    private static KarisomeBlocks instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static KarisomeBlocks plugin() {
        return instance;
    }

}
