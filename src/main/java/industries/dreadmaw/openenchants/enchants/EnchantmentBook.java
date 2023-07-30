package industries.dreadmaw.openenchants.enchants;

import java.util.ArrayList;
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
        level = Integer.parseInt(split[1]);
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        successRate = Integer.parseInt(lore.get(0).split(" ")[2].split("%")[0]);
        destroyRate = Integer.parseInt(lore.get(1).split(" ")[2].split("%")[0]);
        applicableType = lore.get(2).split(" ")[0];
        description = lore.get(3);
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
        if (book.getApplicableType() == "Axe" && (ie.getCurrentItem().getType() == Material.DIAMOND_AXE
                || ie.getCurrentItem().getType() == Material.IRON_AXE
                || ie.getCurrentItem().getType() == Material.STONE_AXE
                || ie.getCurrentItem().getType() == Material.WOOD_AXE)) {
            return true;
        }
        return true;

    }

    public static ItemStack apply(InventoryClickEvent ie, Plugin plugin) {
        EnchantmentBook book = new EnchantmentBook(ie.getCursor());
        Random rand = new Random();
        HumanEntity en = ie.getWhoClicked();
        if (rand.nextInt(100) < book.getSuccessRate()) {
            ItemStack item = ie.getCurrentItem();
            ItemMeta meta = item.hasItemMeta() ? item.getItemMeta()
                    : Bukkit.getItemFactory().getItemMeta(item.getType());
            ArrayList<String> lore = meta.hasLore() ? (ArrayList<String>) meta.getLore() : new ArrayList<String>();
            lore.add(Parse.colors.get(book.getEnchantName()) + book.getEnchantName() + " " + book.getLevel());
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
            return ie.getCurrentItem();
        }
    }

    public ItemStack asItemStack() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(
                Parse.colors.get(getEnchantName()) + "" + ChatColor.BOLD + getEnchantName() + " " + getLevel());

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
