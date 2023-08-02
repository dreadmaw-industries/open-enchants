package industries.dreadmaw.openenchants.enchants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class EnchantmentTemplate {
    private String name;
    private int maxLevel;
    private String applicableType;
    private List<String> description;

    static HashMap<String, List<EnchantmentTemplate>> pool = new HashMap<String, List<EnchantmentTemplate>>() {
        {

            put("Elite", Arrays.asList(
                    new EnchantmentTemplate("Reforged", 5, "Weapon", Arrays.asList(
                            "Repairs your weapon during use."))));
            put("Ultimate", Arrays.asList(
                    new EnchantmentTemplate("Bleed", 6, "Axe", Arrays.asList(
                            "Attacks apply bleed stacks to the target.")),
                    new EnchantmentTemplate("Assassin", 5, "Sword", Arrays.asList(
                            "Attacks at longer range deal less damage,",
                            "but attacks at closer range deal more damage."))));
            put("Legendary", Arrays.asList(
                    new EnchantmentTemplate("Rage", 6, "Weapon", Arrays.asList(
                            "Consecutive hits (up to six) deal more damage.")),
                    new EnchantmentTemplate("Lifesteal", 5, "Sword", Arrays.asList(
                            "Your attacks heal you.")),
                    new EnchantmentTemplate("Devour", 4, "Axe", Arrays.asList(
                            "Your damage is multiplied against people with bleed stacks."))));
        }
    };

    public EnchantmentTemplate(String name, int maxLevel, String applicableType, List<String> description) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.applicableType = applicableType;
        List<String> newdesc = new java.util.ArrayList<String>();
        for (String line : description) {
            newdesc.add(ChatColor.YELLOW + line);
        }
        this.description = newdesc;
    }
    
    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getApplicableType() {
        return applicableType;
    }

    public List<String> getDescription() {
        return description;
    }

}
