package com.github.herobrine.customizeChat;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class CustomizeChat extends JavaPlugin {


    private static CustomizeChat instance;
    public static CustomizeChat getInstance() {return instance;};
    @Override
    public void onEnable() {
        // Plugin startup logic
        //Exaple Of Command : this.getServer().getCommandMap().register(this.getName().toLowerCase(),new spawnCommand("spawn","Shoma Ra Be Spawn Teleport Mikonad","/spawn <player>", List.of("sp")));
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Chat(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
