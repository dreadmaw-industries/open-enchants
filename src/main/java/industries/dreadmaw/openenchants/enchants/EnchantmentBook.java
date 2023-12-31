package industries.dreadmaw.openenchants.enchants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import industries.dreadmaw.openenchants.Apply.Applicable;

public class EnchantmentBook implements Applicable {
    private String enchantName, description;
    private int level;
    private int successRate, destroyRate;
    private String applicableType;

    public EnchantmentBook(String enchantName, int level, int successRate, int destroyRate, String description,
            String applicableType) {
        this.enchantName = enchantName;
        this.level = level;
        this.successRate = successRate;
        this.destroyRate = destroyRate;
        this.description = description;
        this.applicableType = applicableType;
    }

    public EnchantmentBook(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        String[] split = meta.getDisplayName().split(" ");
        enchantName = Parse.strip(split[0]);
        level = Parse.safeParseRoman(split[1]);
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        successRate = Integer.parseInt(lore.get(0).split(" ")[2].split("%")[0]);
        destroyRate = Integer.parseInt(lore.get(1).split(" ")[2].split("%")[0]);
        applicableType = Parse.strip(lore.get(2).split(" ")[0]);
        description = lore.get(3);
    }

    public EnchantmentBook(EnchantmentTemplate template) {
        Random r = new Random();
        this.enchantName = template.getName();
        this.level = r.nextInt(template.getMaxLevel()) + 1;
        this.successRate = r.nextInt(100) + 1;
        this.destroyRate = r.nextInt(100) + 1;
        this.description = template.getDescription().get(0);
        this.applicableType = template.getApplicableType();
    }

    public static ItemStack makeClosed(String tier) {
        ItemStack randomBook = new ItemStack(Material.BOOK);
        ItemMeta meta = randomBook.getItemMeta();
        List<String> lore = Arrays.asList(ChatColor.GRAY + "... then right click an item with the book to apply.");
        meta.setLore(lore);

        String rightClick = ChatColor.RESET + "" + ChatColor.GRAY + " (Right Click)";
        if (tier.equals("Elite")) {
            meta.setDisplayName(ChatColor.BLUE + "Elite Enchantment Book" + rightClick);
        } else if (tier.equals("Ultimate")) {
            meta.setDisplayName(ChatColor.YELLOW + "Ultimate Enchantment Book" + rightClick);
        } else if (tier.equals("Legendary")) {
            meta.setDisplayName(ChatColor.GOLD + "Legendary Enchantment Book" + rightClick);
        }
        randomBook.setItemMeta(meta);
        return randomBook;
    }

    public static boolean isClosedBook(ItemStack item) {
        for (String tier : Arrays.asList("Elite", "Ultimate", "Legendary")) {
            if (makeClosed(tier).getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack makeOpen(String tier) {
        Random r = new Random();
        switch (tier) {
            case "Elite":
                return new EnchantmentBook(EnchantmentTemplate.pool.get("Elite")
                        .get(r.nextInt(EnchantmentTemplate.pool.get("Elite").size()))).toItemStack();
            case "Ultimate":
                return new EnchantmentBook(EnchantmentTemplate.pool.get("Ultimate")
                        .get(r.nextInt(EnchantmentTemplate.pool.get("Ultimate").size()))).toItemStack();
            case "Legendary":
                return new EnchantmentBook(EnchantmentTemplate.pool.get("Legendary")
                        .get(r.nextInt(EnchantmentTemplate.pool.get("Legendary").size()))).toItemStack();
        }
        return null;
    }

    public static boolean isValid(InventoryClickEvent ie) {
        if (!ie.getCursor().getType().equals(Material.BOOK)) {
            return false;
        }
        try {
            new EnchantmentBook(ie.getCursor());
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        EnchantmentBook book = new EnchantmentBook(ie.getCursor());

        String iType = ie.getCurrentItem().getType().toString().toUpperCase();
        if (iType.contains(book.getApplicableType().toUpperCase())) {
            return true;
        }
        if (book.getApplicableType().equals("Weapon") && (iType.contains("AXE") || iType.contains("SWORD"))) {
            return true;
        }
        return false;

    }

    public static ItemStack apply(InventoryClickEvent ie, Plugin plugin) {
        EnchantmentBook book = new EnchantmentBook(ie.getCursor());
        Random rand = new Random();
        HumanEntity en = ie.getWhoClicked();
        ItemStack item = ie.getCurrentItem();
        if (rand.nextInt(100) < book.getSuccessRate()) {
            ItemMeta meta = item.hasItemMeta() ? item.getItemMeta()
                    : Bukkit.getItemFactory().getItemMeta(item.getType());
            ArrayList<String> lore = meta.hasLore() ? (ArrayList<String>) meta.getLore() : new ArrayList<String>();

            String toRemove = "";
            for (String enchantString : lore) {
                if (enchantString.contains(book.getEnchantName()))
                    toRemove = enchantString;
            }
            if (toRemove != "") {
                lore.remove(toRemove);
            }

            lore.add(Parse.colors.get(book.getEnchantName()) + book.getEnchantName() + " "
                    + Parse.safeToRoman(book.getLevel()));
            meta.setLore(lore);
            item.setItemMeta(meta);

            for (int i = 0; i < 25; i++) {
                en.getWorld().playEffect(en.getLocation().add(0, 1, 0), Effect.SPELL, 1);
            }
            en.getWorld().playSound(en.getLocation(), Sound.LEVEL_UP, 5, 0);

            return item;
        }
        for (int i = 0; i < 25; i++) {
            en.getWorld().playEffect(en.getLocation().add(0, 1, 0), Effect.LAVA_POP, 1);
        }
        if (rand.nextInt(100) < book.getDestroyRate()) {
            en.getWorld().playSound(en.getLocation(), Sound.LAVA_POP, 100, 0);
            return new ItemStack(Material.AIR);
        } else {
            return item;
        }
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(
                Parse.colors.get(getEnchantName()) + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + getEnchantName()
                        + " " + Parse.safeToRoman(getLevel()));

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Success Rate: " + getSuccessRate() + "%");
        lore.add(ChatColor.RED + "Destroy Rate: " + getDestroyRate() + "%");
        lore.add(ChatColor.YELLOW + getApplicableType() + " Enchantment");
        lore.add(ChatColor.YELLOW + getDescription());

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
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

    public String getApplicableType() {
        return applicableType;
    }
}
