
package industries.dreadmaw.openenchants.enchants;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.lang.Math;

public class Rage extends Enchantment {
    public Rage(int level, Plugin plugin) {
        super(level, plugin);
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage("Rage proc!");
        Entity e = event.getEntity();
        LivingEntity en;
        if (e instanceof LivingEntity) {
            en = ((LivingEntity) e);
        } else
            return;

        HashMap<String, Integer> OE_rageMap = EnchantmentState.rageMap;
        Entity damager = event.getDamager();
        Player player = (Player) damager;
        String id = en.getEntityId() + "" + player.getEntityId();
        String rid = player.getEntityId() + "" + en.getEntityId();

        // if rage map contains the reverse id, remove it from the map
        if (OE_rageMap.containsKey(rid))
            OE_rageMap.remove(rid);
        // if rage map does not contain the id, add it to the map

        if (!OE_rageMap.containsKey(id))
            OE_rageMap.put(id, 0);
        else {
            // if rage map contains the id, increment the counter
            int counter = OE_rageMap.get(id) + (OE_rageMap.get(id) < 10 ? 1 : 0);

            OE_rageMap.put(id, counter);
            // if counter is less than or equal to 5, increase damage by 10% per stack
            event.setDamage(event.getDamage() * Math.pow(1.04 + (0.025 * level), counter));
            Bukkit.broadcastMessage("Rage " + counter + " " + player.getName() + " " + en.getName() + " Damage : "
                    + event.getFinalDamage());
        }

        if (en.getHealth() <= event.getFinalDamage()) {
            OE_rageMap.remove(id);
            OE_rageMap.remove(rid);
        }

    }

}
