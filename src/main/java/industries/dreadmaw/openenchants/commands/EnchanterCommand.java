package industries.dreadmaw.openenchants.commands;

import org.bukkit.Bukkit;
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
        Inventory inv = Bukkit.createInventory(p, 27, "Enchanting");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
        ItemStack air = new ItemStack(Material.AIR);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, glass);
        }

        inv.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLUE.getData()));
        inv.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData()));
        inv.setItem(13, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData()));
        inv.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData()));
        inv.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.PURPLE.getData()));
        // ItemMeta diaMeta = dia.getItemMeta();

        p.openInventory(inv);
        return false;
    }
}
