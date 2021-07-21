package the_fireplace.audiobook;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

import java.awt.event.KeyEvent;

@Environment(EnvType.CLIENT)
public final class Audiobook implements ClientModInitializer {
	public static final String MODID = "audiobook";
	public static KeyBinding audiobookKey;
	public static KeyBinding stopAudiobookKey;

	@Override
	public void onInitializeClient() {
		audiobookKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.audiobook.read_book", KeyEvent.VK_H, "key.categories.misc"));
		stopAudiobookKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.audiobook.stop_book", KeyEvent.VK_J, "key.categories.misc"));
	}
}
