package io.github.nanodankster.skriptnpc;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import io.github.nanodankster.skriptnpc.util.Metrics;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// DO NOT FORGET "¦" for parse marks again.
public class SkriptNPC extends JavaPlugin {

    private SkriptNPC instance;
    private SkriptAddon addonInstance;

    private FileConfiguration config;
    private File configFile;

    public SkriptNPC() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("skript-npc is warming up...");
        saveMyConfig();
        loadMyConfig();
        if (getServer().getPluginManager().getPlugin("Skript") != null) {
            if (config.getBoolean("enable-metrics")) {
                Metrics metrics = new Metrics(getInstance());
                metrics.addCustomChart(new Metrics.SimplePie("skript_version", () ->
                        getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
                getLogger().info("Metrics are enabled.");
            } else {
                getLogger().info("Metrics are disabled, please enable them!");
            }
            if (Skript.isAcceptRegistrations()) {
                try {
                    getAddonInstance()
                            .loadClasses("io.github.nanodankster.skriptnpc", "skript");
                    getLogger().info("Loaded all addon classes.");
                } catch (IOException e) {
                    getLogger().severe("Cannot load the addon classes to Skript!");
                    e.printStackTrace();
                }
            } else {
                getLogger().severe("Skript is not accepting registrations!");
            }
        } else {
            getLogger().severe("Cannot find Skript!");
        }
        getLogger().info("Finished loading.");
    }

    private void saveMyConfig() {
        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) {
                getLogger().warning("Cannot create the plugin folder!");
            }
        }
        File file = new File(getDataFolder() + "\\config.yml");
        if (!file.exists()) {
            try {
                Files.copy(getResource("config.yml"),file.toPath());
            } catch (IOException e) {
                getLogger().info("Cannot create the configuration file!");
                e.printStackTrace();
            }
        }
        configFile = file;
    }

    private void loadMyConfig() {
        if (configFile != null && configFile.exists()) {
            config = new YamlConfiguration();
            try {
                config.load(configFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    private SkriptAddon getAddonInstance() {
        if (addonInstance == null) {
            addonInstance = Skript.registerAddon(getInstance());
        }
        return addonInstance;
    }

    private SkriptNPC getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    public static void registerPropExpr(final Class<? extends SimplePropertyExpression> exprClass,
                                        Class<?> returnClass, final String property, final String type) {
        String[] propPatterns = {
                "[the] " + property + " of %" + type + "%",
                "%" + type + "%'[s] " + property
        };
        Skript.registerExpression(exprClass, returnClass, ExpressionType.PROPERTY, propPatterns);
    }
}
