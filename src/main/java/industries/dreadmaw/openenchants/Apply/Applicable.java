package industries.dreadmaw.openenchants.Apply;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Applicable {
    protected Plugin plugin;
    public Applicable(Plugin plugin) {
        this.plugin = plugin;
    }
    public static boolean is(ItemStack e) {
        return false;
    }
    public static ItemStack apply(InventoryClickEvent ie) {
        return ie.getCurrentItem();
    }
    
}
