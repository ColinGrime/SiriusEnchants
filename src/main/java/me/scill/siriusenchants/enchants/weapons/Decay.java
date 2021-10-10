package me.scill.siriusenchants.enchants.weapons;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;
import me.scill.siriusenchants.utils.CommonUtil;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")
public class Decay extends CustomEnchant {

	public Decay() {
		super("Decay", 3);
		setRarity(Rarity.Mythical);
		setGear(Gear.CloseCombat);
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		if (!(event.getEntity() instanceof LivingEntity))
			return;

		LivingEntity attackedEntity = (LivingEntity) event.getEntity();
		PotionEffect potionEffect = null;

		switch (level) {
			case 1:
				if (RandomUtil.chance(3))
					potionEffect = CommonUtil.createPotionEffect(PotionEffectType.WITHER, 3, 1);
				break;
			case 2:
				if (RandomUtil.chance(5))
					potionEffect = CommonUtil.createPotionEffect(PotionEffectType.WITHER, 4, 1);
				break;
			case 3:
				if (RandomUtil.chance(7))
					potionEffect = CommonUtil.createPotionEffect(PotionEffectType.WITHER, 5, 1);
				break;
		}

		if (potionEffect != null) {
			PotionApplyByEnchantEvent potionEvent = new PotionApplyByEnchantEvent(this, attackedEntity, potionEffect);
			Bukkit.getServer().getPluginManager().callEvent(potionEvent);
		}
	}
}
