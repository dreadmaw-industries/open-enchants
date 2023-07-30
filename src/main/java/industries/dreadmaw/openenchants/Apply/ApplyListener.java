package industries.dreadmaw.openenchants.Apply;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import industries.dreadmaw.openenchants.Plugin;

public class ApplyListener implements Listener {
    private Plugin plugin;

    public ApplyListener(industries.dreadmaw.openenchants.Plugin plugin) {
        System.out.println("apply listener created");
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (e.getView().getBottomInventory() != e.getView().getTopInventory()) {
            return;
        }
        if (!e.getClick().isLeftClick()) {
            return;
        }
        if (e.getCurrentItem().getType() == org.bukkit.Material.AIR
                || e.getCursor().getData().getItemType() == org.bukkit.Material.AIR) {
            return;
        }
        if (VanillaEnchant.is(e.getCursor())) {
            e.setCurrentItem(VanillaEnchant.apply(e));
            e.setCancelled(true);
            System.out.println("enchanted book!");
            return;
        }
    }

}
