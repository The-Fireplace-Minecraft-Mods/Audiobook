package the_fireplace.audiobook;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;

import java.awt.event.KeyEvent;

public class Audiobook implements ClientModInitializer {
	public static final String MODID = "audiobook";
	public static ModConfig config;
	public static KeyBinding audiobookKey;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		audiobookKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.audiobook.read_book", KeyEvent.VK_H, "key.categories.misc"));
	}
}
