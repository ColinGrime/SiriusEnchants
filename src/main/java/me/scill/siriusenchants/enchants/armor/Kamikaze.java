package me.scill.siriusenchants.enchants.armor;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import org.bukkit.Location;
import org.bukkit.event.entity.PlayerDeathEvent;

@SuppressWarnings("unused")
public class Kamikaze extends CustomEnchant {

	public Kamikaze() {
		super("Kamikaze");
		setGear(Gear.Helmet);
	}

	@Override
	public void onDeath(PlayerDeathEvent event, int level) {
		Location loc = event.getEntity().getLocation();
		event.getEntity().getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2L, false, false);
	}
}
