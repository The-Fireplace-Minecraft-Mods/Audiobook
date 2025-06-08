package dev.the_fireplace.audiobook.mixin;

import dev.the_fireplace.audiobook.AudiobookKeys;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dev.the_fireplace.audiobook.AudiobookLogic;

@Mixin(BookViewScreen.class)
public abstract class BookViewScreenMixin
{
    @Shadow
    private BookViewScreen.BookAccess bookAccess;

    @Inject(at = @At(value = "HEAD"), method = "keyPressed", cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> infoReturnable) {
        if (AudiobookKeys.audiobookKey.matches(keyCode, scanCode)) {
            AudiobookLogic.playBook(bookAccess);
            infoReturnable.setReturnValue(true);
        } else if (AudiobookKeys.stopAudiobookKey.matches(keyCode, scanCode)) {
            AudiobookLogic.stopNarration();
            infoReturnable.setReturnValue(true);
        }
    }
}
