package industries.dreadmaw.openenchants.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchanterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Inventory inv = Bukkit.createInventory(p, 27, "Enchanter");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, glass);
        }
        
        String costPrefix = ChatColor.WHITE + "" + ChatColor.BOLD + "Cost: " + ChatColor.RESET + "" + ChatColor.WHITE;
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.LIGHT_BLUE.getData());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Elite");
        meta.setLore(Arrays.asList(costPrefix + "2,500xp"));
        item.setItemMeta(meta);
        inv.setItem(11, item);

        item = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData());
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Ultimate");
        meta.setLore(Arrays.asList(costPrefix + "5,000xp"));
        item.setItemMeta(meta);
        inv.setItem(13, item);

        item = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.ORANGE.getData());
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Legendary");
        meta.setLore(Arrays.asList(costPrefix + "12,500xp"));
        item.setItemMeta(meta);
        inv.setItem(15, item);

        p.openInventory(inv);
        return false;
    }
}
