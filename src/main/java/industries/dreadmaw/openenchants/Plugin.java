package industries.dreadmaw.openenchants;

import org.bukkit.plugin.java.JavaPlugin;

import industries.dreadmaw.openenchants.commands.*;
import industries.dreadmaw.openenchants.enchants.EListener;
import industries.dreadmaw.openenchants.menus.MenuListener;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("customenchant").setExecutor(new CustomEnchantCommand());
        getCommand("customanvil").setExecutor(new CustomAnvilCommand());
        getCommand("enchanter").setExecutor(new EnchanterCommand());
        getServer().getPluginManager().registerEvents(new EListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        System.out.println("My first plugin has STARTED!!! Hello!!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("TESTING THIS PLUGIN, IS IT WORKING ???????");

    }
}
