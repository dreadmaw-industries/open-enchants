package industries.dreadmaw.openenchants;

import org.bukkit.plugin.java.JavaPlugin;

import industries.dreadmaw.openenchants.Apply.ApplyListener;
import industries.dreadmaw.openenchants.commands.*;
import industries.dreadmaw.openenchants.enchants.EListener;
import industries.dreadmaw.openenchants.menus.*;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("customenchant").setExecutor(new CustomEnchantCommand());
        getCommand("customanvil").setExecutor(new CustomAnvilCommand());
        getServer().getPluginManager().registerEvents(new EListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getServer().getPluginManager().registerEvents(new ApplyListener(this), this);
        System.out.println("My first plugin has started!!! Hello!!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("My first plugin has stopped!!! Bye!!!");

    }
}
