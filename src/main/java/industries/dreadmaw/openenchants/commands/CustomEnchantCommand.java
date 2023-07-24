package industries.dreadmaw.openenchants.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomEnchantCommand implements CommandExecutor {
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

        ItemStack item = p.getItemInHand();
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        if (meta.hasLore() && args.length != 0) {
            lore = meta.getLore();
        } 
        if (args.length != 0) {
            lore.add(myMap.get(args[0]) + args[0] + " " + args[1]);
        } 

        meta.setLore(lore);
        item.setItemMeta(meta);
        p.setItemInHand(item);

        return false;
    }
}
