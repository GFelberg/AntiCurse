package me.GFelberg.AntiCurse.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.GFelberg.AntiCurse.Main;

public class AntiCurseUtils {

    public static String prefix;

    public static void loadVariables() {
        prefix = Main.getInstance().getConfig().getString("AntiCurse.Prefix").replace("&", "§");
    }

    public void reloadConfig(Player p) {

        if (!(p.hasPermission("anticurse.reload"))) {
            p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
        } else {
            Main.getInstance().reloadConfig();
            Main.getInstance().loadVariables();
            p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
            Bukkit.getConsoleSender().sendMessage("===========================================");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "AntiCurse Plugin reloaded");
            Bukkit.getConsoleSender().sendMessage("===========================================");
        }
    }

    public void helpPage(Player p) {
        HelpPageUtils helpUtils = new HelpPageUtils();

        if (!(p.hasPermission("anticurse.admin"))) {
            p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
            p.sendMessage(ChatColor.AQUA + "AntiCurse - Help Commands");
            p.sendMessage(ChatColor.YELLOW + "/ac help: " + helpUtils.getHelp_page());
            p.sendMessage(ChatColor.YELLOW + "/ac : " + helpUtils.getHelp_curse());
            p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
        } else {
            p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
            p.sendMessage(ChatColor.AQUA + "AntiCurse - Help Commands");
            p.sendMessage(ChatColor.YELLOW + "/ac help: " + helpUtils.getHelp_page());
            p.sendMessage(ChatColor.YELLOW + "/ac : " + helpUtils.getHelp_curse());
            p.sendMessage(ChatColor.YELLOW + "/ac reload : " + helpUtils.getHelp_reload());
            p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
        }
    }
}