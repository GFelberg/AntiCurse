package me.GFelberg.AntiCurse;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.AntiCurse.commands.AntiCurse;
import me.GFelberg.AntiCurse.data.AntiCurseSystem;
import me.GFelberg.AntiCurse.utils.AntiCurseUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadVariables();
		getCommand("anticurse").setExecutor(new AntiCurse());
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("AntiCurse Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("AntiCurse Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public void loadVariables() {
		AntiCurseUtils.loadVariables();
		AntiCurseSystem.loadVariables();
	}
}