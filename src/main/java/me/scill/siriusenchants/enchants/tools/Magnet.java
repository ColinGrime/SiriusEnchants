package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.utils.XpUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

@SuppressWarnings("unused")
public class Magnet extends CustomEnchant {

	public Magnet() {
		super("Magnet");
		setRarity(Rarity.Mythical);
		setGear(Gear.Tools);
	}

	@Override
	public void onMine(BlockBreakEvent event, int level) {
		Player player = event.getPlayer();
		XpUtil.changeExp(player, event.getExpToDrop());

		Collection<ItemStack> drops = event.getBlock().getDrops(player.getItemInHand());
		player.getInventory().addItem(drops.toArray(new ItemStack[0]));

		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);
	}
}
