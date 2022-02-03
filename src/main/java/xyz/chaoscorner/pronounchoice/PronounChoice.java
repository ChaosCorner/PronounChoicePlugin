package xyz.chaoscorner.pronounchoice;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.chaoscorner.pronounchoice.commands.PronounCommandManager;
import xyz.chaoscorner.pronounchoice.services.PronounGroupService;
import xyz.chaoscorner.pronounchoice.services.UserPronounService;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class PronounChoice extends JavaPlugin {
    public static Set<PronounGroup> pronounGroups;

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

        // initialize services
        PronounGroupService pronounGroupService = new PronounGroupService(luckPerms);
        UserPronounService userPronounService = new UserPronounService(luckPerms);

        // load pronoun groups into memory on startup for faster looking and whatnot
        pronounGroups = new HashSet<>(pronounGroupService.getPronounGroups());

        // initialize the main pronoun command
        Objects.requireNonNull(getCommand("pronoun")).setExecutor(new PronounCommandManager(pronounGroupService, userPronounService));
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin shutting down...");
    }
}
