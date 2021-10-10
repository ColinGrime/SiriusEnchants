package me.scill.siriusenchants;

import me.scill.siriusenchants.commands.*;
import me.scill.siriusenchants.data.EnchantsConfig;
import me.scill.siriusenchants.data.PluginConfig;
import me.scill.siriusenchants.listeners.ArmorEquipListeners;
import me.scill.siriusenchants.listeners.EnchantAttachListener;
import me.scill.siriusenchants.listeners.EnchantStackListeners;
import me.scill.siriusenchants.listeners.enchants.ArmorListeners;
import me.scill.siriusenchants.listeners.enchants.ToolListeners;
import me.scill.siriusenchants.listeners.enchants.WeaponListeners;
import me.scill.siriusenchants.menus.setup.PanelListeners;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public final class SiriusEnchants extends JavaPlugin {

	private static SiriusEnchants plugin;
	private EnchantsConfig enchantsConfig;
	private EnchantsManager enchantsManager;

	@Override
	public void onEnable() {
		plugin = this;
		PluginConfig.loadPluginConfig(this);

		enchantsConfig = new EnchantsConfig(this);
		enchantsManager = new EnchantsManager();

		enchantsConfig.loadEnchantsConfig();
		loadEnchantsManager();

		setupCommands();
		setupListeners();
	}

	@Override
	public void onDisable() {

	}

	private void setupCommands() {
		getServer().getPluginCommand("ce").setExecutor(new EnchantCommand(this));
		getServer().getPluginCommand("ceadmin").setExecutor(new AdminCommand(this));
		getServer().getPluginCommand("unenchant").setExecutor(new UnenchantCommand(this));
		getServer().getPluginCommand("transmute").setExecutor(new TransmuteCommand());
		getServer().getPluginCommand("sirius").setExecutor(new SiriusCommand(this));
	}

	private void setupListeners() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new EnchantAttachListener(this), this);
		pm.registerEvents(new EnchantStackListeners(this), this);
		pm.registerEvents(new ArmorEquipListeners(this), this);

		pm.registerEvents(new ArmorListeners(enchantsManager), this);
		pm.registerEvents(new WeaponListeners(enchantsManager), this);
		pm.registerEvents(new ToolListeners(enchantsManager), this);

		pm.registerEvents(new PanelListeners(), this);
	}

	public void loadEnchantsManager() {
		try {
			enchantsManager.loadEnchants(getFile());
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static SiriusEnchants getPlugin() {
		return plugin;
	}

	public EnchantsConfig getEnchantsConfig() {
		return enchantsConfig;
	}

	public EnchantsManager getEnchantsManager() {
		return enchantsManager;
	}
}
