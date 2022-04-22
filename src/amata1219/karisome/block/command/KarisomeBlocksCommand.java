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

        long durationInTicks;
        if (args.length <= 1) durationInTicks = 20 * 3;
        else {
            try {
                durationInTicks = Long.parseLong(args[1]);
            } catch(NumberFormatException ex) {
                player.sendMessage("%sThe duration must be numeric.".formatted(RED));
                return true;
            }
        }

        if (!(1 <= durationInTicks && durationInTicks <= 20 * 60 * 30)) {
            sender.sendMessage("%sThe duration must be between 1 and 36000.".formatted(RED));
            return true;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.5f);

        player.getInventory().addItem(KarisomeBlockItem.createKarisomeBlockItemStack(amount, durationInTicks));

        player.sendMessage("%sYou have been given %d karisome blocks!".formatted(AQUA, amount));
        return true;
    }
}
