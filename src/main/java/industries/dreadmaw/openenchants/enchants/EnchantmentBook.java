package industries.dreadmaw.openenchants.enchants;

import org.bukkit.ChatColor;

public class EnchantmentBook {
    private String enchantName, description;
    private int level;
    private int successRate, destroyRate;

    public EnchantmentBook(String enchantName, int level, int successRate, int destroyRate) {
        this(enchantName, level, successRate, destroyRate, "");
    }

    public EnchantmentBook(String enchantName, int level, int successRate, int destroyRate, String description) {
        this.enchantName = enchantName;
        this.level = level;
        this.successRate = successRate;
        this.destroyRate = destroyRate;
        this.description = description;
    }

    public EnchantmentBook(String enchantmentBookString) {
        String[] lines = enchantmentBookString.split("\n");
        if (lines.length < 2) { // all enchantmentBook strings will have >= 2 lines
            System.out.println(lines.length);
            throw new IllegalArgumentException("Improper format for a enchantmentBook string!");
        }
        this.enchantName = lines[0].split(" ")[0];
        this.level = Integer.parseInt(lines[0].split(" ")[1]);

        String successDestroyRatesLine = lines[1];
        int numPos1, numPos2;

        numPos1 = successDestroyRatesLine.indexOf('%');
        numPos2 = successDestroyRatesLine.lastIndexOf('%');

        this.successRate = Integer.parseInt(successDestroyRatesLine.substring(numPos1 - 2, numPos1));
        this.destroyRate = Integer.parseInt(successDestroyRatesLine.substring(numPos2 - 2, numPos2));

        this.description = "";
        if (lines.length >= 3) {
            for (int i = 2; i < lines.length; i++) {
                if (i != 2) { this.description += "\n"; }
                this.description += lines[i];
            }
        }
    }

    public String getEnchantmentBookString() {
        String t = getEnchantName() + " " + getLevel();
        t += String.format("\n%s%2d%% %s%2d%%", ChatColor.GREEN, getSuccessRate(), ChatColor.RED, getDestroyRate());
        return (t + "\n" + ChatColor.WHITE + getDescription() + "\n");
    }

    public String getEnchantName() {
        return enchantName;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public int getDestroyRate() {
        return destroyRate;
    }
}
