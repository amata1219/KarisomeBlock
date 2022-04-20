package amata1219.karisome.block.command;

import static org.bukkit.ChatColor.*;

import amata1219.karisome.block.KarisomeBlockItem;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KarisomeBlocksCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("%sThis command can only be executed by the player.".formatted(RED));
            return true;
        }

        int amount;
        if (args.length == 0) amount = 1;
        else {
            try {
                amount = Integer.parseInt(args[0]);
            } catch(NumberFormatException ex) {
                player.sendMessage("%sThe amount must be numeric.".formatted(RED));
                return true;
            }
        }

        if (!(1 <= amount && amount <= 64)) {
            sender.sendMessage("%sThe amount must be between 1 and 64.".formatted(RED));
            return true;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.5f);

        player.getInventory().addItem(KarisomeBlockItem.createKarisomeBlockItemStack(amount, 20 * 3));

        player.sendMessage("%sYou have been given %d karisome blocks!".formatted(AQUA, amount));
        return true;
    }
}
