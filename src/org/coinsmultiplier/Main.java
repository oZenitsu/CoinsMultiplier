package org.coinsmultiplier;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.coinsmultiplier.commands.ReloadCommand;
import org.coinsmultiplier.events.DeathEvent;

public class Main extends JavaPlugin {
    private static Main instance;
    private Economy econ;

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
        registerEvents();
        if (!setupEconomy()) {
            getLogger().severe("Vault not found! please download it first.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
    }

    public void registerCommands() {
        getCommand("cmreload").setExecutor(new ReloadCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
