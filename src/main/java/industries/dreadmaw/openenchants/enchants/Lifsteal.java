package industries.dreadmaw.openenchants.enchants;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class Lifsteal extends Enchantment {
    public Lifsteal(int level, Plugin plugin) {
        super(level, plugin);
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage("Lifesteal proc!");
        Entity damager = event.getDamager();
        Player player = (Player) damager;
        player.setHealth(Math.min(player.getHealth() + this.level, player.getMaxHealth()));
    }

}
