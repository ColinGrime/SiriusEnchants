package me.scill.siriusenchants.listeners.enchants;

import me.scill.siriusenchants.EnchantsManager;
import me.scill.siriusenchants.events.ArmorEquipEvent;
import me.scill.siriusenchants.events.PotionApplyByEnchantEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorListeners implements Listener {

	private final EnchantsManager manager;

	public ArmorListeners(EnchantsManager manager) {
		this.manager = manager;
	}

	/**
	 * Enchants are activated when players are attacked.
	 */
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			for (ItemStack item : ((Player) event.getEntity()).getInventory().getArmorContents())
				manager.getCustomEnchants(item).forEach((key, value) -> key.onDamage(event, value));
		}
	}

	/**
	 * Enchants are activated when players are damaged.
	 */
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			for (ItemStack item : ((Player) event.getEntity()).getInventory().getArmorContents())
				manager.getCustomEnchants(item).forEach((key, value) -> key.onDamage(event, value));
		}
	}

	/**
	 * Enchants are activated when players move.
	 */
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (event.getTo() == null || (event.getFrom().getBlockX() == event.getTo().getBlockX()
				&& event.getFrom().getBlockZ() == event.getTo().getBlockZ()))
			return;

		ItemStack item = event.getPlayer().getInventory().getBoots();
		manager.getCustomEnchants(item).forEach((key, value) -> key.onMove(event, value));
	}

	/**
	 * Enchants are activated when players are killed.
	 */
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		ItemStack item = event.getEntity().getInventory().getHelmet();
		manager.getCustomEnchants(item).forEach((key, value) -> key.onDeath(event, value));
	}

	/**
	 * Enchants are activated when other enchants are used on an entity.
	 */
	@EventHandler
	public void onPotionApplyByEnchant(PotionApplyByEnchantEvent event) {
		manager.getCustomEnchants().forEach(e -> e.onPotionApply(event));
		if (!event.isCancelled())
			event.getEntity().addPotionEffect(event.getPotionEffect());
	}

	/**
	 * Enchants are activated when armor is equipped.
	 */
	@EventHandler
	public void onArmorEquip(ArmorEquipEvent event) {
		manager.getCustomEnchants(event.getOldArmor()).forEach((key, value) -> key.onArmorEquip(event, value, false));
		manager.getCustomEnchants(event.getNewArmor()).forEach((key, value) -> key.onArmorEquip(event, value, true));
	}
}
