package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.ArmorEquipEvent;
import me.scill.siriusenchants.utils.CommonUtil;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")
public class Pyromancer extends CustomEnchant {

	public Pyromancer() {
		super("Pyromancer");
		setRarity(Rarity.Rare);
		setGear(Gear.Chestplate);
	}

	@Override
	public void onArmorEquip(ArmorEquipEvent event, int level, boolean isOn) {
		Player player = event.getPlayer();

		if (isOn)
			player.addPotionEffect(CommonUtil.createPotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 1));
		else
			player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	}
}
