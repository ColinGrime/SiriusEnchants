package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;
import me.scill.siriusenchants.utils.CommonUtil;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")
public class Amber extends CustomEnchant {

	public Amber() {
		super("Amber", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.Leggings);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		if (!(event.getDamager() instanceof LivingEntity))
			return;

		LivingEntity attackedEntity = (LivingEntity) event.getDamager();
		PotionEffect potionEffect = null;

		switch (level) {
			case 1:
				if (RandomUtil.chance(3))
					potionEffect = CommonUtil.createPotionEffect(PotionEffectType.SLOW, 3, 1);
				break;
			case 2:
				if (RandomUtil.chance(5))
					potionEffect = CommonUtil.createPotionEffect(PotionEffectType.SLOW, 4, 1);
				break;
			case 3:
				if (RandomUtil.chance(7))
					potionEffect = CommonUtil.createPotionEffect(PotionEffectType.SLOW, 5, 1);
				break;
		}

		if (potionEffect != null)
			new PotionApplyByEnchantEvent(this, attackedEntity, potionEffect);
	}
}
