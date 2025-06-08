package dev.the_fireplace.audiobook.entrypoints;

import dev.the_fireplace.audiobook.AudiobookConstants;
import dev.the_fireplace.audiobook.AudiobookKeys;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

@Mod.EventBusSubscriber(modid = AudiobookConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegistryHandler
{
    @SubscribeEvent
    public static void registerBindings(FMLClientSetupEvent event) {
        AudiobookKeys.audiobookKey = new KeyMapping("key.audiobook.read_book", KeyEvent.VK_H, "key.categories.misc");
        AudiobookKeys.stopAudiobookKey = new KeyMapping("key.audiobook.stop_book", KeyEvent.VK_J, "key.categories.misc");
        ClientRegistry.registerKeyBinding(AudiobookKeys.audiobookKey);
        ClientRegistry.registerKeyBinding(AudiobookKeys.stopAudiobookKey);
    }
}
