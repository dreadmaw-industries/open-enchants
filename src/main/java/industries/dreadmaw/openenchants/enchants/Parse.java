package industries.dreadmaw.openenchants.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import industries.dreadmaw.openenchants.Plugin;

public class Parse {
    static Enchantment getEnchantment(String enchantName, int level, Plugin plugin) {
        if (enchantName.equals(ChatColor.YELLOW + "Bleed")) {
            return new Bleed(level, plugin);
        } else if (enchantName.equals(ChatColor.GOLD + "Lifesteal")) {
            return new Lifsteal(level, plugin);
        } else {
            return null;
        }
    }
    static List<Enchantment> parseEnchantments(List<String> lore, Plugin plugin) {
        List<Enchantment> enchants = new ArrayList<Enchantment>();
        for (String line : lore) {
            String[] split = line.split(" ");
            if (split.length !=  2) {
                continue;
            }
            String enchantName = split[0];
            int level = Integer.parseInt(split[1]);
            Enchantment enchant = getEnchantment(enchantName, level, plugin);
            if (enchant != null) {
                enchants.add(enchant);
            }
        }
        return enchants;
    }
}
