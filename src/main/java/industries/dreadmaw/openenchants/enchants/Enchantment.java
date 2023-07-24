package industries.dreadmaw.openenchants.enchants;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class Enchantment {
    protected int level;
    protected Plugin plugin;
    public Enchantment(int level, Plugin plugin) {
        this.level = level;
        this.plugin = plugin;
    }
    public void onDamage(EntityDamageByEntityEvent event) {}
}
