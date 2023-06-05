package me.GFelberg.AntiCurse.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.AntiCurse.data.AntiCurseSystem;
import me.GFelberg.AntiCurse.utils.AntiCurseUtils;

public class AntiCurse implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("anticurse")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players");
				return true;
			}

			if (!(sender.hasPermission("anticurse.anticurse"))) {
				sender.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
				return true;
			}

			Player p = (Player) sender;
			AntiCurseUtils utils = new AntiCurseUtils();
			AntiCurseSystem sys = new AntiCurseSystem();

			if (args.length == 0) {
				sys.removeCurse(p);
				return true;
			}

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					utils.reloadConfig(p);
				} else if (args[0].equalsIgnoreCase("help")) {
					utils.helpPage(p);
				}
				return true;
			}
		}
		return true;
	}
}