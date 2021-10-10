package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class Smelter extends CustomEnchant {

	public Smelter() {
		super("Smelter");
		setGear(Gear.Pickaxe);
	}

	@Override
	public void onMine(BlockBreakEvent event, int level) {
		Block block = event.getBlock();
		ItemStack item = event.getPlayer().getItemInHand();

		ItemStack[] drops = block.getDrops(item).toArray(new ItemStack[0]);
		for (ItemStack drop : drops) {
			if (drop.getType() == Material.IRON_ORE) {
				block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
				block.getDrops().clear();
			} else if (drop.getType() == Material.GOLD_ORE) {
				block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
				block.getDrops().clear();
			}
		}
	}
}
