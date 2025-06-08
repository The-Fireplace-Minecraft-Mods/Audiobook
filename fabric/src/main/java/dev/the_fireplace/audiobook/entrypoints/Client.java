package dev.the_fireplace.audiobook.entrypoints;

import dev.the_fireplace.audiobook.AudiobookKeys;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

import java.awt.event.KeyEvent;

@Environment(EnvType.CLIENT)
public final class Client implements ClientModInitializer
{
    @Override
    public void onInitializeClient() {
        AudiobookKeys.audiobookKey = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.audiobook.read_book", KeyEvent.VK_H, "key.categories.misc"));
        AudiobookKeys.stopAudiobookKey = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.audiobook.stop_book", KeyEvent.VK_J, "key.categories.misc"));
    }
}
