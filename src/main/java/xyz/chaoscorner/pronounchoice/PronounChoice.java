package xyz.chaoscorner.pronounchoice;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.chaoscorner.pronounchoice.commands.PronounCommandManager;
import xyz.chaoscorner.pronounchoice.util.LuckPermsDataAccess;

public final class PronounChoice extends JavaPlugin {
    @Override
    public void onEnable() {
        // connect to luckperms
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) {
            getLogger().severe("Cannot load LuckPerms provider. Is LuckPerms working?");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        LuckPerms luckPerms = provider.getProvider();

        // create the data access lib
        LuckPermsDataAccess luckPermsDA = new LuckPermsDataAccess(luckPerms);

        // initialize the main pronoun command
        getCommand("pronoun").setExecutor(new PronounCommandManager(luckPermsDA));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
