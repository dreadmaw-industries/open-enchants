package industries.dreadmaw.openenchants.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        Inventory inventory = Bukkit.createInventory(null, 9, "My first inventory");

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("My first item");
        meta.setLore(Arrays.asList(ChatColor.YELLOW + "Bleed 5", "This is another lore line"));
        item.setItemMeta(meta);
        
        inventory.setItem(0, item);
        
        p.openInventory(inventory);

        return false;
    }

}
