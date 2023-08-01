package industries.dreadmaw.openenchants.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import industries.dreadmaw.openenchants.enchants.EnchantmentBook;

public class GiveBook implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        EnchantmentBook testBook = new EnchantmentBook(args[0], Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                Integer.parseInt(args[4]), "Test description.", args[1]);
        p.getInventory().addItem(testBook.asItemStack());

        return false;
    }
}
