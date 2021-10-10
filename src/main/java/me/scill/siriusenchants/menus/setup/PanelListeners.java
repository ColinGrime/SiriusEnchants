package me.scill.siriusenchants.menus.setup;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PanelListeners implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (!Panel.getPanelViewers().containsKey(player))
			return;

		event.setCancelled(true);

		Panel panel = Panel.getPanelViewers().get(player);
		Panel.MenuAction action = panel.getActions().get(event.getRawSlot());

		if (action != null)
			action.click((Player) event.getWhoClicked(), event.getClick());
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Panel.getPanelViewers().remove((Player) event.getPlayer());
	}
}
