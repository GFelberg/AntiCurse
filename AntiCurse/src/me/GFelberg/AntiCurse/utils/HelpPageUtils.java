package me.GFelberg.AntiCurse.utils;

import me.GFelberg.AntiCurse.Main;

public class HelpPageUtils {

	public String getHelp_page() {
		return Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
	}

	public String getHelp_curse() {
		return Main.getInstance().getConfig().getString("Help.Curse").replace("&", "§");
	}

	public String getHelp_reload() {
		return Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}
}