package industries.dreadmaw.openenchants.Apply;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import industries.dreadmaw.openenchants.Plugin;
import industries.dreadmaw.openenchants.enchants.EnchantmentBook;

public class ApplyListener implements Listener {
    private Plugin plugin;

    public ApplyListener(industries.dreadmaw.openenchants.Plugin plugin) {
        System.out.println("apply listener created");
        this.plugin = plugin;
    }

    public void decrementAndSet(InventoryClickEvent e) {
        ItemStack i = e.getCursor();
        int amount = i.getAmount();
        if (amount == 1) {
            i = new ItemStack(org.bukkit.Material.AIR);
        } else {
            System.out.println(i.toString() + " " + amount + " " + (amount - 1));
            i.setAmount(amount - 1);
        }

        final ItemStack f = i;
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                e.getWhoClicked().setItemOnCursor(f);
            }
        };
        runnable.runTaskLater(plugin, 1L);
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        // if (e.getView().getBottomInventory() != e.getView().getTopInventory()) {
        //     return;
        // }
        if (!(e.getClick().isRightClick() || e.getClick().isCreativeAction())) {
            return;
        }
        if (e.getCurrentItem().getType() == org.bukkit.Material.AIR
                || e.getCursor().getData().getItemType() == org.bukkit.Material.AIR) {
            return;
        }
        if (VanillaEnchant.isValid(e)) {
            e.setCurrentItem(VanillaEnchant.apply(e));
            System.out.println("vanilla enchanted book!");
        } else if (EnchantmentBook.isValid(e)) {
            e.setCurrentItem(EnchantmentBook.apply(e, plugin));
            System.out.println("custom enchanted book!");
        } else {
            return;
        }
        decrementAndSet(e);
        e.setCancelled(true);
    }

}
