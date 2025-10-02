package com.github.herobrine.customizeChat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.*;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Chat implements Listener {
    private CustomizeChat plugin = CustomizeChat.getInstance();


    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        var player = event.getPlayer();
        var format = plugin.getConfig().getString("messages.format");
        var prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        event.renderer((source, playerName, message, viewer) ->
                MiniMessage.miniMessage().deserialize(
                        format,
                        Placeholder.unparsed("player", player.getName()),
                        Placeholder.component("message", message),
                        Placeholder.unparsed("prefix", prefix)
                )
                );
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.joinMessage(MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("messages.join"), Placeholder.unparsed("player", event.getPlayer().getName())));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.quitMessage((MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("messages.leave"), Placeholder.unparsed("player", event.getPlayer().getName()))));
    }
}