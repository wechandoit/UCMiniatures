package net.miyukichan;

import net.miyukichan.commands.MainCommand;
import net.miyukichan.configuration.ConfigurationManager;
import net.miyukichan.listeners.MachineListeners;
import net.miyukichan.mob.MobsManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the MiniatureMobs project.
 */
public class Main extends JavaPlugin {

    @Getter private static Main instance;

    public MobsManager getMobsManager() {
        return mobsManager;
    }

    public void setMobsManager(MobsManager mobsManager) {
        this.mobsManager = mobsManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    @Getter public MobsManager mobsManager;
    @Getter public ConfigurationManager configurationManager;

    public static Main getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        instance = this;

        registerEvents();
        this.loadMobsManager();
        this.loadConfigurationManager();

        configurationManager.registerConfigMobs();

        getCommand("miniaturemobs").setExecutor(new MainCommand());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        mobsManager.removeAll();
    }

    /**
     *  Initializes the {@link MobsManager}.
     */
    private void loadMobsManager() {
        this.mobsManager = new MobsManager();
    }

    /**
     *  Initializes the {@link ConfigurationManager}.
     */
    private void loadConfigurationManager() {
        this.configurationManager = new ConfigurationManager();
    }

    /**
     * Register all the listener in the project.
     */
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new MachineListeners(), this);
    }

}
