import me.scill.siriusenchants.listeners.EnchantAttachListener;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnchantAttachTest {

	private InventoryClickEvent event;
	private EnchantAttachListener listener;

	@Before
	public void setUp() {
		event = mock(InventoryClickEvent.class);
		listener = mock(EnchantAttachListener.class);
	}

	@Test
	public void testAttachNull() {
		when(event.getCurrentItem()).thenReturn(new ItemStack(Material.DIAMOND_AXE));
		when(event.getCursor()).thenReturn(null);

		listener.onInventoryClick(event);

		assertThat(event.getCurrentItem()).isNotNull();
		assertThat(event.getCursor()).isNull();
		assertThat(event.isCancelled()).isFalse();
	}

	@Test
	public void testAttachAir() {
		when(event.getCurrentItem()).thenReturn(new ItemStack(Material.DIAMOND_AXE));
		when(event.getCursor()).thenReturn(new ItemStack(Material.AIR));

		listener.onInventoryClick(event);

		assertThat(event.getCurrentItem()).isNotNull();
		assertThat(event.getCursor()).isNotNull();
		assertThat(event.isCancelled()).isFalse();
	}

	@Test
	public void testAttachRegularEnchantedBook() {
		when(event.getCurrentItem()).thenReturn(new ItemStack(Material.DIAMOND_AXE));
		when(event.getCursor()).thenReturn(new ItemStack(Material.ENCHANTED_BOOK));

		listener.onInventoryClick(event);

		assertThat(event.getCurrentItem()).isNotNull();
		assertThat(event.getCursor()).isNotNull();
		assertThat(event.isCancelled()).isFalse();
	}
}
