package me.scill.siriusenchants.enums;

import org.bukkit.Material;

import java.util.*;

public enum Gear {

	Sword(Material.DIAMOND_SWORD, Material.IRON_SWORD),
	Bow(Material.BOW),
	Pickaxe(Material.DIAMOND_PICKAXE, Material.IRON_PICKAXE),
	Axe(Material.DIAMOND_AXE, Material.IRON_AXE),
	//Shovel(Material.DIAMOND_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE),
	Hoe(Material.DIAMOND_HOE, Material.IRON_HOE),
	FishingRod(Material.FISHING_ROD),
	Shears(Material.SHEARS),
	Helmet(Material.DIAMOND_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET),
	Chestplate(Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE),
	Leggings(Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS),
	Boots(Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS),
	CloseCombat(Sword, Axe),
	Tools(Pickaxe, Axe, Hoe, FishingRod, Shears),
	Weapons(Sword, Bow, Axe),
	Armor(Helmet, Chestplate, Leggings, Boots),
	All(Armor, Weapons, Tools);

	private final Set<Material> materials = new HashSet<>();

	Gear(Material... materials) {
		this.materials.addAll(Arrays.asList(materials));
	}

	Gear(Gear... gear) {
		Arrays.stream(gear).forEach(g -> materials.addAll(g.getMaterials()));
	}

	public Set<Material> getMaterials() {
		return materials;
	}
}
