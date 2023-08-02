package industries.dreadmaw.openenchants.enchants;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import industries.dreadmaw.openenchants.Plugin;

public class OpenBookListener implements org.bukkit.event.Listener {
    private Plugin plugin;

    public OpenBookListener(industries.dreadmaw.openenchants.Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (! (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (!EnchantmentBook.isClosedBook(item)) {
            return;
        }
        int amount = item.getAmount();
        if (amount == 1) {
            e.getPlayer().getInventory().remove(item);
        } else {
            item.setAmount(amount - 1);
            e.getPlayer().setItemInHand(item);
        }
        String tier = Parse.strip(item.getItemMeta().getDisplayName().split(" ")[0]);
        e.getPlayer().getInventory().addItem(EnchantmentBook.makeOpen(tier));

        Location loc = e.getPlayer().getLocation();
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        FireworkEffect.Builder effect = FireworkEffect.builder().with(Type.BALL_LARGE).flicker(true);
        fwm.setPower(0);

        if (tier.equals("Elite")) {
            effect = effect.withColor(Color.AQUA);
        } else if (tier.equals("Ultimate")) {
            effect = effect.withColor(Color.YELLOW);
        } else if (tier.equals("Legendary")) {
            effect = effect.withColor(Color.ORANGE);
        }

        fwm.addEffect(effect.build());
        fw.setFireworkMeta(fwm);
    }
}