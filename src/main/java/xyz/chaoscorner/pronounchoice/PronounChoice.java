package xyz.chaoscorner.pronounchoice;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PronounChoice extends JavaPlugin {
    private final Logger logger = getLogger();
    private final FileConfiguration config = getConfig();
    private final JdbcPooledConnectionSource connection = new JdbcPooledConnectionSource();

    // TODO: see https://www.baeldung.com/ormlite
    @Override
    public void onEnable() {
        // initialize connection source
    }

    @Override
    public void onDisable() {
        // close connection
    }
}
