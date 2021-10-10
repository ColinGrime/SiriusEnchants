package me.scill.siriusenchants.enchants.tools;

import me.scill.siriusenchants.CustomEnchant;
import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Excavator extends CustomEnchant {

	public Excavator() {
		super("Excavator", 2);
		setRarity(Rarity.Rare);
		setGear(Gear.Pickaxe);
	}

	@Override
	public void onMine(BlockBreakEvent event, int level) {
		Player player = event.getPlayer();
		float pitch = player.getLocation().getPitch();
		float yaw = player.getLocation().getYaw();
		if (yaw < 0)
			yaw += 360;

		Location blockLocation = event.getBlock().getLocation();
		List<Location> blockLocations;

		if (Math.abs(pitch) > 45)
			blockLocations = getLocationsBetween(blockLocation.clone().add(level, 0, level), blockLocation.clone().add(-level, 0, -level));
		else if ((yaw >= 315 || yaw <= 45) || (yaw >= 135 && yaw <= 225))
			blockLocations = getLocationsBetween(blockLocation.clone().add(level, level, 0), blockLocation.clone().add(-level, -level, 0));
		else
			blockLocations = getLocationsBetween(blockLocation.clone().add(0, level, level), blockLocation.clone().add(0, -level, -level));

		blockLocations.stream().filter(l -> l.getBlock().getType() != Material.BEDROCK)
							   .forEach(l -> l.getBlock().breakNaturally(player.getItemInHand()));
	}

	private List<Location> getLocationsBetween(final Location location1, final Location location2) {
		double lowX = Math.min(location1.getX(), location2.getX());
		double lowY = Math.min(location1.getY(), location2.getY());
		double lowZ = Math.min(location1.getZ(), location2.getZ());

		List<Location> locations = new ArrayList<>();
		for (int blockX = 0; blockX <= Math.abs(location1.getBlockX() - location2.getBlockX()); blockX++) {
			for (int blockY = 0; blockY <= Math.abs(location1.getBlockY() - location2.getBlockY()); blockY++) {
				for (int blockZ = 0; blockZ <= Math.abs(location1.getBlockZ() - location2.getBlockZ()); blockZ++)
					locations.add(new Location(location1.getWorld(), lowX + blockX, lowY + blockY, lowZ + blockZ));
			}
		}

		return locations;
	}
}
