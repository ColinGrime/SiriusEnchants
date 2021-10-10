package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Armor;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.ArmorEquipEvent;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class Blessed extends CustomEnchant {

	public Blessed() {
		super("Blessed");
		setRarity(Rarity.Mythical);
		setGear(Gear.Armor);
	}

	@Override
	public void onArmorEquip(ArmorEquipEvent event, int level, boolean isOn) {
		Player player = event.getPlayer();
		Armor armor = isOn ? Armor.getArmor(event.getNewArmor()) : Armor.getArmor(event.getOldArmor());

		switch (armor) {
			case Helmet:
				setHealth(player, 2, isOn);
				break;
			case Chestplate:
				setHealth(player, 4, isOn);
				break;
			case Leggings:
				setHealth(player, 3, isOn);
				break;
			case Boots:
				setHealth(player, 1, isOn);
				break;
		}
	}

	private void setHealth(Player player, int health, boolean isOn) {
		if (!isOn)
			health *= -1;

		player.setMaxHealth(player.getMaxHealth() + health);
	}
}
