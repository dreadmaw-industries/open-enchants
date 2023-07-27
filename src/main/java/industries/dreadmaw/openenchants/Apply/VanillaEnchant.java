package industries.dreadmaw.openenchants.Apply;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import industries.dreadmaw.openenchants.Plugin;

public class VanillaEnchant extends Applicable {
    public VanillaEnchant(Plugin plugin) {
        super(plugin);
    }

    public static boolean is(ItemStack e) {
        return e.getType() == org.bukkit.Material.ENCHANTED_BOOK;
    }

    public static ItemStack apply(InventoryClickEvent ie) {
        ItemStack cursor = ie.getCursor();
        ItemStack current = ie.getCurrentItem();

        EnchantmentStorageMeta emeta = (EnchantmentStorageMeta) cursor.getItemMeta();
        ItemMeta meta = current.getItemMeta();
        for (org.bukkit.enchantments.Enchantment e : emeta.getStoredEnchants().keySet()) {
            meta.addEnchant(e, emeta.getStoredEnchants().get(e), true);
        }
        current.setItemMeta(meta);

        HumanEntity en = ie.getWhoClicked();
        for (int i = 0; i < 25; i++) {
            en.getWorld().playEffect(en.getLocation().add(0, 1, 0), Effect.SPELL, 1);
        }
        en.getWorld().playSound(en.getLocation(), Sound.LEVEL_UP, 5, 0);
        return current;
    }

}
