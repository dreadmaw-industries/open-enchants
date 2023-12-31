package industries.dreadmaw.openenchants;

import org.bukkit.plugin.java.JavaPlugin;

import industries.dreadmaw.openenchants.Apply.ApplyListener;
import industries.dreadmaw.openenchants.commands.*;
import industries.dreadmaw.openenchants.enchants.EListener;
import industries.dreadmaw.openenchants.enchants.OpenBookListener;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("givebook").setExecutor(new GiveBookCommand());
        getCommand("customanvil").setExecutor(new CustomAnvilCommand());
        getCommand("enchanter").setExecutor(new EnchanterCommand());
        getServer().getPluginManager().registerEvents(new EListener(this), this);
        getServer().getPluginManager().registerEvents(new ApplyListener(this), this);
        getServer().getPluginManager().registerEvents(new EnchanterListener(this), this);
        getServer().getPluginManager().registerEvents(new OpenBookListener(this), this);
        System.out.println("My first plugin has started!!! Hello!!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("TESTING THIS PLUGIN, IS IT WORKING ???????");

    }
}
