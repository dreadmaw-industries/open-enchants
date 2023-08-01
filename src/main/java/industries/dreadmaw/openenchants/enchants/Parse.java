package industries.dreadmaw.openenchants.enchants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;

import industries.dreadmaw.openenchants.Plugin;

public class Parse {
    public static HashMap<String, ChatColor> colors = new HashMap<String, ChatColor>() {
            {
                put("Rage", ChatColor.GOLD);
                put("Devour", ChatColor.GOLD);
                put("Lifesteal", ChatColor.GOLD);
                put("Bleed", ChatColor.YELLOW);
            }
        };
    
    public static HashMap<String, Integer> romanToInt = new HashMap<String, Integer>() {
        {
            put("I", 1);
            put("II", 2);
            put("III", 3);
            put("IV", 4);
            put("V", 5);
            put("VI", 6);
            put("VII", 7);
            put("IIX", 8);
            put("IX", 9);
            put("X", 10);
        }
    };
    public static HashMap<Integer, String> intToRoman = romanToInt.entrySet().stream()
            .collect(HashMap::new, (m, v) -> m.put(v.getValue(), v.getKey()), HashMap::putAll);
    
    static Integer safeParseRoman(String roman) {
        return romanToInt.containsKey(roman) ? romanToInt.get(roman) : Integer.parseInt(roman);
    }

    static String safeToRoman(int number) {
        return intToRoman.containsKey(number) ? intToRoman.get(number) : Integer.toString(number);
    }
    static String strip(String enchantName) {
        enchantName = enchantName.replace(ChatColor.BOLD.toString(), "");
        enchantName = enchantName.replace(ChatColor.UNDERLINE.toString(), "");
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
            return new Rage(level, plugin);
        } else if (enchantName.equals(ChatColor.GOLD + "Devour")) {
            return new Devour(level, plugin);
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
            Integer level = safeParseRoman(split[1]);
            Enchantment enchant = getEnchantment(enchantName, level, plugin);
            if (enchant != null) {
                enchants.add(enchant);
            }
        }
        return enchants;
    }
}
