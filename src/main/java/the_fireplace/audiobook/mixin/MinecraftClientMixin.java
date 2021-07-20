package the_fireplace.audiobook.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import the_fireplace.audiobook.Audiobook;
import the_fireplace.audiobook.AudiobookLogic;

import javax.annotation.Nullable;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

	@Shadow @Nullable public ClientPlayerEntity player;

	@Inject(at = @At(value="HEAD"), method = "handleInputEvents")
	private void handleInputEvents(CallbackInfo info) {
		if (Audiobook.audiobookKey.isPressed() && player != null) {
			if (AudiobookLogic.isReadableBook(player.getStackInHand(Hand.MAIN_HAND))) {
				AudiobookLogic.playBook(player.getStackInHand(Hand.MAIN_HAND));
			} else if (AudiobookLogic.isReadableBook(player.getStackInHand(Hand.OFF_HAND))) {
				AudiobookLogic.playBook(player.getStackInHand(Hand.OFF_HAND));
			}
		} else if(Audiobook.stopAudiobookKey.isPressed()) {
			AudiobookLogic.stopNarration();
		}
	}
}
