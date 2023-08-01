package industries.dreadmaw.openenchants.enchants;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class Assassin extends Enchantment {
    public Assassin(int level, Plugin plugin) {
        super(level, plugin);
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage("Assassin proc!");
        Entity damager = event.getDamager();
        Player player = (Player) damager;
         LivingEntity en;
        if (e instanceof LivingEntity) {
           en = ((LivingEntity) e);
        }
        else return;
        double range = player.getLocation().distance(en.getLocation());
        
        event.setDamage(event.getDamage() +(  1.5 + abs(1.5 - range) ));
         Bukkit.broadcastMessage("Assassin " + range  + " "+ player.getName() + " " + en.getName() + " Damage : " + event.getFinalDamage());

    }

}
