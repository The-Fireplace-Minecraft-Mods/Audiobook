package the_fireplace.audiobook.mixin;

import net.minecraft.client.gui.screen.ingame.BookScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import the_fireplace.audiobook.Audiobook;
import the_fireplace.audiobook.AudiobookLogic;

@Mixin(BookScreen.class)
public abstract class BookScreenMixin {
	@Shadow private BookScreen.Contents contents;

	@Inject(at = @At(value="HEAD"), method = "keyPressed", cancellable = true)
	private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> infoReturnable) {
		if(Audiobook.audiobookKey.matchesKey(keyCode, scanCode)) {
			AudiobookLogic.playBook(contents);
			infoReturnable.setReturnValue(true);
		} else if(Audiobook.stopAudiobookKey.matchesKey(keyCode, scanCode)) {
			AudiobookLogic.stopNarration();
			infoReturnable.setReturnValue(true);
		}
	}
}
