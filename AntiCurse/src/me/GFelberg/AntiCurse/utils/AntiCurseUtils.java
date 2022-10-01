package me.GFelberg.AntiCurse.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.GFelberg.AntiCurse.Main;

public class AntiCurseUtils {

	public static String prefix, nocurse, removed, noitem;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("AntiCurse.Prefix").replace("&", "§");
		nocurse = Main.getInstance().getConfig().getString("AntiCurse.NoCurse").replace("&", "§");
		removed = Main.getInstance().getConfig().getString("AntiCurse.Removed").replace("&", "§");
		noitem = Main.getInstance().getConfig().getString("AntiCurse.NoItemHand").replace("&", "§");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("anticurse.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
		} else {
			Main.getInstance().reloadConfig();
			loadVariables();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getServer().getConsoleSender().sendMessage("===========================================");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "AntiCurse Plugin reloaded");
			Bukkit.getServer().getConsoleSender().sendMessage("===========================================");
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

	public void removeCurse(Player p) {
		ItemStack item = p.getInventory().getItemInMainHand();

		if (item == null || item.getType() == Material.AIR) {
			p.sendMessage(noitem);
		}

		else if (item.getItemMeta() instanceof EnchantmentStorageMeta) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			if (meta.hasStoredEnchant(Enchantment.VANISHING_CURSE)
					|| meta.hasStoredEnchant(Enchantment.BINDING_CURSE)) {
				meta.removeStoredEnchant(Enchantment.VANISHING_CURSE);
				meta.removeStoredEnchant(Enchantment.BINDING_CURSE);
				item.setItemMeta(meta);
				p.sendMessage(AntiCurseUtils.removed);
			} else {
				p.sendMessage(AntiCurseUtils.nocurse);
			}
		}

		else if (item.containsEnchantment(Enchantment.BINDING_CURSE)
				|| item.containsEnchantment(Enchantment.VANISHING_CURSE)) {
			item.removeEnchantment(Enchantment.VANISHING_CURSE);
			item.removeEnchantment(Enchantment.BINDING_CURSE);
			p.sendMessage(AntiCurseUtils.removed);
		} else {
			p.sendMessage(AntiCurseUtils.nocurse);
		}
	}
}
