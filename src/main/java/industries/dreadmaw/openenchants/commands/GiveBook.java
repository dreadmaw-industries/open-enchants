package industries.dreadmaw.openenchants.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import industries.dreadmaw.openenchants.enchants.EnchantmentBook;

public class GiveBook implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, ChatColor> myMap = new HashMap<String, ChatColor>() {
            {
                put("Rage", ChatColor.GOLD);
                put("Lifesteal", ChatColor.GOLD);
                put("Bleed", ChatColor.YELLOW);
            }
        };
        Player p = (Player) sender;

        EnchantmentBook testBook = new EnchantmentBook(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                Integer.parseInt(args[3]), "Test description.", "Axe");
        p.getInventory().addItem(testBook.asItemStack());

        return false;
    }
}
