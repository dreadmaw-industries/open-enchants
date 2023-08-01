package industries.dreadmaw.openenchants.enchants;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import industries.dreadmaw.openenchants.Plugin;

public class Bleed extends Enchantment {
    public Bleed(int level, Plugin plugin) {
        super(level, plugin);
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {

        Bukkit.broadcastMessage("Bleed proc!");
        // With BukkitRunnable
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Entity en = event.getEntity();

                List<MetadataValue> lm = en.getMetadata("OE_bleedTicks");
                int tickCount = lm.get(0).asInt();
                lm = en.getMetadata("OE_bleedStacks");
                int bleedStacks = lm.get(0).asInt();

                if (en.isDead() || tickCount <= 0) {
                    en.removeMetadata("OE_bleedTicks", plugin);
                    en.removeMetadata("OE_bleedStacks", plugin);
                    this.cancel();
                    return;
                }

                if (tickCount-- % 20 == 0) {
                    // for (int i = 0; i < bleedStacks * 10; i++) {
                    //     en.getWorld().playEffect(en.getLocation().add(0, 1, 0), Effect.TILE_BREAK, 152);
                    // }
                    for (int i = 0; i < lm.get(0).asInt(); i++)
                        en.getWorld().playEffect(en.getLocation(),  Effect.STEP_SOUND, 152, 8);
                    Bukkit.broadcastMessage("Bleed tick with " + bleedStacks + " stacks");
                    ((LivingEntity) event.getEntity()).damage(1 * bleedStacks);
                }
                if (tickCount % 40 == 0) {
                    lm = en.getMetadata("OE_bleedStacks");
                    bleedStacks = Math.max(lm.get(0).asInt()-1, 1);
                    en.setMetadata("OE_bleedStacks", new FixedMetadataValue(plugin, bleedStacks));
                }

                lm = en.getMetadata("OE_bleedTicks");
                tickCount = lm.get(0).asInt();

                en.setMetadata("OE_bleedTicks", new FixedMetadataValue(plugin, tickCount - 1));
            }
        };

        event.getEntity().setMetadata("OE_bleedTicks", new FixedMetadataValue(plugin, 100L));
        List<MetadataValue> lm = event.getEntity().getMetadata("OE_bleedStacks");

        if (lm.size() == 0) {
            runnable.runTaskTimer(plugin, 0L, 1L);
        }

        int bleedStacks = lm.size() > 0 ? lm.get(0).asInt() : 0;
        event.getEntity().setMetadata("OE_bleedStacks",
                new FixedMetadataValue(plugin, Math.min(bleedStacks + 1, level)));
    }
}
