package dev.the_fireplace.audiobook.mixin;

import dev.the_fireplace.audiobook.AudiobookKeys;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dev.the_fireplace.audiobook.AudiobookLogic;

@Mixin(Screen.class)
public final class ScreenMixin
{
    @Inject(at = @At(value = "HEAD"), method = "keyPressed")
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (AudiobookKeys.stopAudiobookKey.matches(keyCode, scanCode)) {
            AudiobookLogic.stopNarration();
        }
    }
}
