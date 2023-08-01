package industries.dreadmaw.openenchants.enchants;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.MetadataValue;

import industries.dreadmaw.openenchants.Plugin;

public class Devour extends Enchantment {
    public Devour(int level, Plugin plugin) {
        super(level, plugin);
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage("Devour proc!");

        Entity en = event.getEntity();
        if (en.hasMetadata("OE_bleedStacks")) {
            List<MetadataValue> lm = en.getMetadata("OE_bleedStacks");
            
            for (int i = 0; i < lm.get(0).asInt(); i++)
                en.getWorld().playEffect(en.getLocation(), Effect.STEP_SOUND, 152, 8);

            int bleedStacks = lm.get(0).asInt();

            event.setDamage(event.getDamage() * Math.pow(1.04 + (0.025 * level), bleedStacks));
        }
    }

}
