package industries.dreadmaw.openenchants.enchants;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import industries.dreadmaw.openenchants.Plugin;

public class EListener implements org.bukkit.event.Listener {
    private Plugin plugin;

    public EListener(industries.dreadmaw.openenchants.Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        String rid = damager.getEntityId() + "" + event.getEntity().getEntityId();
        //if rage map contains the reverse id, remove it from the map
        if(EnchantmentState.rageMap.containsKey(rid))
            EnchantmentState.rageMap.remove(rid);
        if (!(damager instanceof Player)) {
        return;
        }
        ItemStack item = ((Player) event.getDamager()).getItemInHand();
        if (item.getType() == Material.AIR)
            return;
        if (item.getItemMeta().hasLore()) {
            List<Enchantment> enchantments = Parse.parseEnchantments(item.getItemMeta().getLore(), plugin);
            for (Enchantment enchantment : enchantments) {
                enchantment.onDamage(event);
            }
        }
    }

}
