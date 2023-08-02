package industries.dreadmaw.openenchants.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import industries.dreadmaw.openenchants.Plugin;
import industries.dreadmaw.openenchants.enchants.EnchantmentBook;
import industries.dreadmaw.openenchants.enchants.Parse;

public class EnchanterListener implements org.bukkit.event.Listener {
    private Plugin plugin;

    public EnchanterListener(industries.dreadmaw.openenchants.Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (!(e.getClickedInventory().getName() == "Enchanter"))
            return;

        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        String msg = ChatColor.RED + "" + ChatColor.BOLD + "You do not have enough experience!";
        String name = Parse.strip(e.getCurrentItem().getItemMeta().getDisplayName());

        switch (name) {
            case "Elite":
                if (p.getTotalExperience() >= 2500) {
                    int prev = p.getTotalExperience();
                    p.setLevel(0);
                    p.setTotalExperience(0);
                    p.giveExp(prev -2500);

                    p.getInventory().addItem(EnchantmentBook.makeClosed("Elite"));
                } else {
                    p.sendMessage(msg);
                }
                return;
            case "Ultimate":
                if (p.getTotalExperience() >= 5000) {
                    int prev = p.getTotalExperience();
                    p.setLevel(0);
                    p.setTotalExperience(0);
                    p.giveExp(prev -5000);

                    p.getInventory().addItem(EnchantmentBook.makeClosed("Ultimate"));
                } else {
                    p.sendMessage(msg);
                }
                return;
            case "Legendary":
                if (p.getTotalExperience() >= 12500) {
                    int prev = p.getTotalExperience();
                    p.setLevel(0);
                    p.setTotalExperience(0);
                    p.giveExp(prev -12500);

                    p.getInventory().addItem(EnchantmentBook.makeClosed("Legendary"));
                } else {
                    p.sendMessage(msg);
                }
                return;
        }
    }
}