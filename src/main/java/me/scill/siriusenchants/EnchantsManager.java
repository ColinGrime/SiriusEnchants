package me.scill.siriusenchants;

import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTList;
import me.scill.siriusenchants.data.Settings;
import me.scill.siriusenchants.utils.FileUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class EnchantsManager {

	private Map<String, CustomEnchant> customEnchants;

	/**
	 * Reloads the main manager by initalizing all the
	 * CustomEnchant classes into a map with its ID.
	 *
	 * @param file SiriusEnchants plugin location
	 */
	public void loadEnchants(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		customEnchants = new HashMap<>();

		for (String className : FileUtil.getClassNames(file, "me.scill.siriusenchants.enchants.")) {
			Class<?> clazz = Class.forName("me.scill.siriusenchants.enchants." + className);

			CustomEnchant customEnchant = (CustomEnchant) clazz.getConstructor().newInstance();
			customEnchant.setEnchantItems(this);
			customEnchant.setMeta(Settings.enchantNames.get(customEnchant.getId()), Settings.enchantLores.get(customEnchant.getId()));
			customEnchant.getRarity().addEnchant(customEnchant);

			customEnchants.put(customEnchant.getId(), customEnchant);
		}
	}

	public CustomEnchant getCustomEnchant(String id) {
		return customEnchants.get(id);
	}

	public List<CustomEnchant> getCustomEnchants() {
		return new ArrayList<>(customEnchants.values());
	}

	/**
	 * Retrieves all CustomEnchant objects that are attached to an ItemStack.
	 *
	 * @param item any valid ItemStack
	 * @return list of CustomEnchant objects
	 */
	public Map<CustomEnchant, Integer> getCustomEnchants(ItemStack item) {
		if (item == null)
			return new HashMap<>();

		NBTItem nbtItem = new NBTItem(item);
		NBTList<String> enchantsList = nbtItem.getStringList("enchants");

		Map<CustomEnchant, Integer> customEnchants = new HashMap<>();

		for (String enchant : enchantsList) {
			String id = enchant.split(":")[0];
			int level = Integer.parseInt(enchant.split(":")[1]);

			customEnchants.put(getCustomEnchant(id), level);
		}

		return customEnchants;
	}

	private ItemStack setCustomEnchants(ItemStack item, Map<CustomEnchant, Integer> enchants) {
		NBTItem nbtItem = new NBTItem(item);
		NBTList<String> enchantsList = nbtItem.getStringList("enchants");

		for (Map.Entry<CustomEnchant, Integer> enchant : enchants.entrySet())
			enchantsList.add(enchant.getKey().getId() + ":" + enchant.getValue());

		return nbtItem.getItem();
	}

	/**
	 * Adds a CustomEnchant to an ItemStack.
	 *
	 * @param item any valid ItemStack
	 * @param enchant any CustomEnchant object
	 * @return true if the CustomEnchant was successfully attached (no duplicates)
	 */
	public ItemStack addCustomEnchant(ItemStack item, CustomEnchant enchant, int level) {
		if (item == null || !enchant.getGear().getMaterials().contains(item.getType()) && item.getType() != Material.ENCHANTED_BOOK)
			return null;

		Map<CustomEnchant, Integer> customEnchants = getCustomEnchants(item);
		if (customEnchants.containsKey(enchant))
			return null;

		customEnchants.put(enchant, level);
		item = setCustomEnchants(item, customEnchants);

		if (item.getType() == Material.ENCHANTED_BOOK)
			return item;

		ItemMeta meta = item.getItemMeta();
		String enchantName = enchant.getItem(level).getItemMeta().getDisplayName();

		if (!meta.hasLore() || meta.getLore() == null || meta.getLore().isEmpty())
			meta.setLore(Collections.singletonList(enchantName));
		else {
			List<String> updatedLore = meta.getLore();
			updatedLore.add(enchantName);
			meta.setLore(updatedLore);
		}

		item.setItemMeta(meta);
		return item;
	}

	/**
	 * Removes a CustomEnchant from an ItemStack.
	 *
	 * @param item any valid ItemStack
	 * @param enchant any CustomEnchant object
	 */
	public ItemStack removeCustomEnchant(ItemStack item, CustomEnchant enchant) {
		if (item == null || item.getItemMeta() == null || item.getType() == Material.ENCHANTED_BOOK)
			return null;

		Map<CustomEnchant, Integer> customEnchants = getCustomEnchants(item);
		if (!customEnchants.containsKey(enchant))
			return null;

		String enchantName = enchant.getItem(customEnchants.get(enchant)).getItemMeta().getDisplayName();

		customEnchants.remove(enchant);
		item = setCustomEnchants(item, customEnchants);

		ItemMeta meta = item.getItemMeta();
		if (meta.hasLore() && meta.getLore() != null && !meta.getLore().isEmpty()) {
			List<String> updatedLore = meta.getLore();
			updatedLore.remove(enchantName);
			meta.setLore(updatedLore);
		}

		item.setItemMeta(meta);
		return item;
	}
}
