package me.scill.siriusenchants.enchants.weapons;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.SiriusEnchants;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.utils.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class Thor extends CustomEnchant implements Listener {

	private final Map<Player, List<LightningStrike>> lightning = new HashMap<>();

	public Thor() {
		super("Thor", 3);
		setRarity(Rarity.Rare);
		setGear(Gear.Axe);

		Bukkit.getServer().getPluginManager().registerEvents(this, SiriusEnchants.getPlugin());
	}

	@Override
	public void onDamage(EntityDamageByEntityEvent event, int level) {
		Player attacker = (Player) event.getDamager();
		Entity attacked = event.getEntity();

		switch (level) {
			case 3:
				tryStrikeLightning(attacker, attacked);
			case 2:
				tryStrikeLightning(attacker, attacked);
			case 1:
				tryStrikeLightning(attacker, attacked);
		}
	}

	private void tryStrikeLightning(Player attacker, Entity attacked) {
		if (RandomUtil.chance(3)) {
			List<LightningStrike> lightningStrikes = lightning.getOrDefault(attacker, new ArrayList<>());
			lightningStrikes.add(attacked.getWorld().strikeLightning(attacked.getLocation()));
			lightning.put(attacker, lightningStrikes);
		}
	}

	@EventHandler
	public void onLightningStrike(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof LightningStrike)
				|| !(event.getEntity() instanceof Player)
				|| !lightning.containsKey((Player) event.getEntity()))
			return;

		Player attacked = (Player) event.getEntity();
		LightningStrike lightningStrike = (LightningStrike) event.getDamager();

		List<LightningStrike> lightningStrikes = lightning.get(attacked);
		if (lightningStrikes.contains(lightningStrike)) {
			lightningStrikes.remove(lightningStrike);
			lightning.put(attacked, lightningStrikes);

			event.setDamage(0);
		}
	}
}
