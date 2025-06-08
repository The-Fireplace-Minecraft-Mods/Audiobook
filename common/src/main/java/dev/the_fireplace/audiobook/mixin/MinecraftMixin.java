package dev.the_fireplace.audiobook.mixin;

import dev.the_fireplace.audiobook.AudiobookKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.the_fireplace.audiobook.AudiobookLogic;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin
{
    @Shadow
    @Nullable
    public LocalPlayer player;

    @Inject(at = @At(value = "HEAD"), method = "handleKeybinds")
    private void handleKeybinds(CallbackInfo info) {
        if (AudiobookKeys.audiobookKey.isDown() && player != null) {
            if (AudiobookLogic.isReadableBook(player.getItemInHand(InteractionHand.MAIN_HAND))) {
                AudiobookLogic.playBook(player.getItemInHand(InteractionHand.MAIN_HAND));
            } else if (AudiobookLogic.isReadableBook(player.getItemInHand(InteractionHand.OFF_HAND))) {
                AudiobookLogic.playBook(player.getItemInHand(InteractionHand.OFF_HAND));
            }
        } else if (AudiobookKeys.stopAudiobookKey.isDown()) {
            AudiobookLogic.stopNarration();
        }
    }
}
