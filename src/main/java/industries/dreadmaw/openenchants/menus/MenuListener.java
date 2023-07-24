package industries.dreadmaw.openenchants.menus;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class MenuListener implements Listener {
    private Plugin plugin;

    public MenuListener(industries.dreadmaw.openenchants.Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("Enchanting")){ //dont forget to include ChatColor formatting
            
            //Make sure the player clicked on an item and not on the inventory
            if(e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().hasItemMeta() == false) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
                e.setCancelled(true);
                return;
            }

            //make it so that players cannot move items in the inventory
            e.setCancelled(true);

        }
    }
    
}
