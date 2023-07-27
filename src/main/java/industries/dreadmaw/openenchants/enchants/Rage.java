
package industries.dreadmaw.openenchants.enchants;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.lang.Math;
import java.util.Map;
public class Rage extends Enchantment {
    private  Map<String, Integer> OE_rageMap ;

    public Rage(int level, Plugin plugin, HashMap<String, Integer> rageMap) {
        super(level, plugin);
        OE_rageMap = rageMap;
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage("Rage proc!");
        Entity e = event.getEntity();
        LivingEntity en;
        if (e instanceof LivingEntity) {
           en = ((LivingEntity) e);
        }
        else return ;
        Entity damager = event.getDamager();
        Player player = (Player) damager;
        String id = en.getEntityId() + "" + player.getEntityId();
        String rid = player.getEntityId() + "" + en.getEntityId();
        //if rage map contains the reverse id, remove it from the map
        if(OE_rageMap.containsKey(rid))
            OE_rageMap.remove(rid);
        //if rage map does not contain the id, add it to the map
        
        if(!OE_rageMap.containsKey(id))
            OE_rageMap.put(id,0);
        else{
            //if rage map contains the id, increment the counter
            int counter = OE_rageMap.get(id) +1;
            Bukkit.broadcastMessage("Rage" + counter + player.getName() + en.getName() );

            OE_rageMap.put(id, counter);
            //if counter is less than or equal to 5, increase damage by 10% per stack
            if(counter >6 )
               event.setDamage(event.getDamage() * Math.pow(1.04 + level/10,6));

        }

        if(en.getHealth() <= event.getFinalDamage()) {
            OE_rageMap.remove(id);
            OE_rageMap.remove(rid);
        }


        
        
        
         
       
        
    }
   

}
