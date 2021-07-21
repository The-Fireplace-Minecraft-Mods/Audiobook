package the_fireplace.audiobook.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import the_fireplace.audiobook.Audiobook;
import the_fireplace.audiobook.AudiobookLogic;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public final class ScreenMixin {

    @Inject(at = @At(value="HEAD"), method = "keyPressed")
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (Audiobook.stopAudiobookKey.matchesKey(keyCode, scanCode)) {
            AudiobookLogic.stopNarration();
        }
    }
}
