package industries.dreadmaw.openenchants.enchants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;

import industries.dreadmaw.openenchants.Plugin;

public class Parse {
    static HashMap<String, Integer> rageMap = new HashMap<String, Integer>();
    public static HashMap<String, ChatColor> colors = new HashMap<String, ChatColor>() {
            {
                put("Rage", ChatColor.GOLD);
                put("Lifesteal", ChatColor.GOLD);
                put("Bleed", ChatColor.YELLOW);
            }
        };
        
    static String strip(String enchantName) {
        enchantName = enchantName.replace(ChatColor.BOLD.toString(), "");
        for (Map.Entry<String, ChatColor> entry : colors.entrySet()) {
            enchantName = enchantName.replace(entry.getValue().toString(), "");
        }
        return enchantName;
    }
    static Enchantment getEnchantment(String enchantName, int level, Plugin plugin) {
        if (enchantName.equals(ChatColor.YELLOW + "Bleed")) {
            return new Bleed(level, plugin);
        } else if (enchantName.equals(ChatColor.GOLD + "Lifesteal")) {
            return new Lifesteal(level, plugin);
        } else if (enchantName.equals(ChatColor.GOLD + "Rage")) {
            return new Rage(level, plugin,rageMap);
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
