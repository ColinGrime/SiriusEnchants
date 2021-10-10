package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.ArmorEquipEvent;
import me.scill.siriusenchants.utils.CommonUtil;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")
public class Fierce extends CustomEnchant {

	public Fierce() {
		super("Fierce", 2);
		setRarity(Rarity.Mythical);
		setGear(Gear.Chestplate);
	}

	@Override
	public void onArmorEquip(ArmorEquipEvent event, int level, boolean isOn) {
		Player player = event.getPlayer();

		if (isOn) {
			switch (level) {
				case 1:
					player.addPotionEffect(CommonUtil.createPotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 1));
					break;
				case 2:
					player.addPotionEffect(CommonUtil.createPotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 2));
					break;
			}
		} else
			player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	}
}
