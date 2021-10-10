package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;
import me.scill.siriusenchants.utils.CommonUtil;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")
public class Fusion extends CustomEnchant {

	public Fusion() {
		super("Fusion", 3);
		setRarity(Rarity.Rare);
		setGear(Gear.Leggings);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		Player attackedPlayer = (Player) event.getEntity();
		PotionEffect potionEffect = null;

		switch (level) {
			case 1:
				potionEffect = applyEffects(attackedPlayer, 2);
				break;
			case 2:
				potionEffect = applyEffects(attackedPlayer, 5);
				break;
			case 3:
				potionEffect = applyEffects(attackedPlayer, 8);
				break;
		}

		if (potionEffect != null)
			new PotionApplyByEnchantEvent(this, attackedPlayer, potionEffect);
	}

	private PotionEffect applyEffects(Player attackedPlayer, int activationChance) {
		if (RandomUtil.chance(activationChance)) {
			if (RandomUtil.chance(50))
				return CommonUtil.createPotionEffect(PotionEffectType.REGENERATION, 6, 2);
			else
				return CommonUtil.createPotionEffect(PotionEffectType.INCREASE_DAMAGE, 6, 3);
		}

		return null;
	}
}
