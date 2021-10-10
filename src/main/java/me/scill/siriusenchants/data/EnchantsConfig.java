package me.scill.siriusenchants.data;

import me.scill.siriusenchants.SiriusEnchants;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class EnchantsConfig {

	private FileConfiguration enchantsConfig;

	public EnchantsConfig(SiriusEnchants plugin) {
		createEnchantsConfig(plugin);
	}

	private void createEnchantsConfig(SiriusEnchants plugin) {
		File enchantsFile = new File(plugin.getDataFolder(), "enchants.yml");
		if (!enchantsFile.exists()) {
			enchantsFile.getParentFile().mkdirs();
			plugin.saveResource("enchants.yml", false);
		}

		enchantsConfig = new YamlConfiguration();
		try {
			enchantsConfig.load(enchantsFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void loadEnchantsConfig() {
		for (String enchantId : enchantsConfig.getConfigurationSection("").getKeys(false)) {
			String formattedEnchantId = enchantId.substring(0, 1).toUpperCase() + enchantId.substring(1);
			Settings.enchantNames.put(formattedEnchantId, enchantsConfig.getString(enchantId + ".name"));
			Settings.enchantLores.put(formattedEnchantId, enchantsConfig.getStringList(enchantId + ".lore"));
		}
	}
}
