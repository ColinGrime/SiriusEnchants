package me.scill.siriusenchants.events;

import me.scill.siriusenchants.CustomEnchant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

public class PotionApplyByEnchantEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;

	private final CustomEnchant customEnchant;
	private final LivingEntity entity;
	private final PotionEffect potionEffect;

	public PotionApplyByEnchantEvent(CustomEnchant customEnchant, LivingEntity entity, PotionEffect potionEffect) {
		this.customEnchant = customEnchant;
		this.entity = entity;
		this.potionEffect = potionEffect;
	}

	public CustomEnchant getCustomEnchant() {
		return customEnchant;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public PotionEffect getPotionEffect() {
		return potionEffect;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
