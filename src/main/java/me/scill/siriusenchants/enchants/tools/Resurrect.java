package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class Resurrect extends CustomEnchant {

	public Resurrect() {
		super("Resurrect");
		setRarity(Rarity.Mythical);
		setGear(Gear.Tools);
	}

	@Override
	public void onItemDamage(PlayerItemDamageEvent event, int level) {
		if (event.getItem().getItemMeta() == null)
			return;

		ItemStack item = event.getItem();

		if (item.getDurability() + event.getDamage() > item.getType().getMaxDurability()) {
			event.setCancelled(true);
			item.setDurability((short) 0);

			ItemStack updatedItem = SiriusEnchants.getPlugin().getEnchantsManager().removeCustomEnchant(item, this);
			event.getPlayer().setItemInHand(updatedItem);
		}
	}
}
