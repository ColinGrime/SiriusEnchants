package me.scill.siriusenchants;

import me.scill.siriusenchants.enums.Gear;
import me.scill.siriusenchants.enums.Rarity;
import me.scill.siriusenchants.events.ArmorEquipEvent;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;
import me.scill.siriusenchants.utils.CommonUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CustomEnchant {

	private final Map<Integer, ItemStack> enchantItems = new HashMap<>();
	private final Map<Integer, Integer> enchantCapacity = new HashMap<>();

	private final String id;
	private final int maxLevel;

	private Rarity rarity = Rarity.Common;
	private Gear gear = Gear.All;

	protected CustomEnchant(String id) {
		this(id, 1);
	}

	protected CustomEnchant(String id, int maxLevel) {
		this.id = id;
		this.maxLevel = maxLevel;
	}

	// All listeners available.
	public void onMine(BlockBreakEvent event, int level) {}
	public void onInteract(PlayerInteractEvent event, int level) {}
	public void onDamage(EntityDamageByEntityEvent event, int level) {}
	public void onDamage(EntityDamageEvent event, int level) {}
	public void onMove(PlayerMoveEvent event, int level) {}
	public void onDeath(PlayerDeathEvent event, int level) {}
	public void onItemDamage(PlayerItemDamageEvent event, int level) {}
	public void onPotionApply(PotionApplyByEnchantEvent event) {}
	public void onArmorEquip(ArmorEquipEvent event, int level, boolean isOn) {}

	public String getId() {
		return id;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public ItemStack getItem(int level) {
		return enchantItems.get(level);
	}

	public void givePlayerItem(int level, Player player) {
		givePlayerItem(level, player, "&aYou have received the %enchant% &aenchant!");
	}

	/**
	 * Gives the player a custom enchant book.
	 *
	 * @param level enchant level
	 * @param player any player
	 * @param message message displayed to player
	 */
	public void givePlayerItem(int level, Player player, String message) {
		ItemStack item = getItem(level);
		player.getInventory().addItem(item);

		String name = item.getItemMeta().getDisplayName();
		CommonUtil.sendMessage(player, message.replaceAll("%enchant%", name));
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Gear getGear() {
		return gear;
	}

	public void setEnchantItems(EnchantsManager manager) {
		// Creates an ItemStack for each level of the custom enchant.
		for (int i=0; i<=maxLevel; i++) {
			ItemStack enchant = manager.addCustomEnchant(new ItemStack(Material.ENCHANTED_BOOK), this, i);
			enchant.setAmount(i == 0 ? 1 : i);

			enchantItems.put(i, enchant);
		}
	}

	public void setMeta(String name, List<String> lore) {
		// Loops through each level's ItemStack and adds an individual ItemMeta to them.
		for (Map.Entry<Integer, ItemStack> enchantItem : enchantItems.entrySet()) {
			ItemMeta meta = enchantItem.getValue().getItemMeta();
			String newName = name;

			// Some books need roman numerals next to them to tell the difference.
			if (enchantItem.getKey() > 0 && enchantItems.keySet().size() > 2)
				newName += " &7(&9" + convertToRomanNumeral(enchantItem.getKey()) + "&7)";

			meta.setDisplayName(CommonUtil.color(newName));
			meta.setLore(CommonUtil.color(lore));

			enchantItem.getValue().setItemMeta(meta);
		}
	}

	private String convertToRomanNumeral(int number) {
		switch (number) {
			case 1:
				return "I";
			case 2:
				return "II";
			case 3:
				return "III";
			case 4:
				return "IV";
			default:
				return "";
		}
	}

	protected void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	protected void setGear(Gear gear) {
		this.gear = gear;
	}

	protected void setCapacity(int level, int capacity) {
		enchantCapacity.put(level, capacity);
	}
}
