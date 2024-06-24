package me.GFelberg.AntiCurse.data;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import me.GFelberg.AntiCurse.Main;

public class AntiCurseSystem {

    public static String curse_notfound, curse_removed, curse_noitemhand;

    public static void loadVariables() {
        curse_notfound = Main.getInstance().getConfig().getString("AntiCurse.NoCurse").replace("&", "§");
        curse_removed = Main.getInstance().getConfig().getString("AntiCurse.Removed").replace("&", "§");
        curse_noitemhand = Main.getInstance().getConfig().getString("AntiCurse.NoItemHand").replace("&", "§");
    }

    public void removeCurse(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item == null || item.getType() == Material.AIR) {
            p.sendMessage(curse_noitemhand);
        } else if (item.getItemMeta() instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            if (meta.hasStoredEnchant(Enchantment.VANISHING_CURSE)
                    || meta.hasStoredEnchant(Enchantment.BINDING_CURSE)) {
                meta.removeStoredEnchant(Enchantment.VANISHING_CURSE);
                meta.removeStoredEnchant(Enchantment.BINDING_CURSE);
                item.setItemMeta(meta);
                p.sendMessage(curse_removed);
            } else {
                p.sendMessage(curse_notfound);
            }
        } else if (item.containsEnchantment(Enchantment.BINDING_CURSE)
                || item.containsEnchantment(Enchantment.VANISHING_CURSE)) {
            item.removeEnchantment(Enchantment.VANISHING_CURSE);
            item.removeEnchantment(Enchantment.BINDING_CURSE);
            p.sendMessage(curse_removed);
        } else {
            p.sendMessage(curse_notfound);
        }
    }
}
