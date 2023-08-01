package industries.dreadmaw.openenchants.Apply;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public interface Applicable {
    public static boolean isValid(InventoryClickEvent ie) {
        return false;
    }
    public static ItemStack apply(InventoryClickEvent ie, Plugin plugin) {
        return ie.getCurrentItem();
    }
    
}
