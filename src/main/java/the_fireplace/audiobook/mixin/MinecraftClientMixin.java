package the_fireplace.audiobook.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.WritableBookItem;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import the_fireplace.audiobook.Audiobook;
import the_fireplace.audiobook.AudiobookLogic;

import javax.annotation.Nullable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

	@Shadow @Nullable public ClientPlayerEntity player;

	@Inject(at = @At(value="HEAD"), method = "handleInputEvents")
	private void handleInputEvents(CallbackInfo info) {
		if(Audiobook.audiobookKey.isPressed() && player != null) {
			//noinspection ConstantConditions
			if((player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof WrittenBookItem || player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof WritableBookItem) && player.getStackInHand(Hand.MAIN_HAND).hasTag() && !BookScreen.readPages(player.getStackInHand(Hand.MAIN_HAND).getTag()).isEmpty())
				AudiobookLogic.playBook(player.getStackInHand(Hand.MAIN_HAND));
			else if(player.getStackInHand(Hand.OFF_HAND).getItem() instanceof WrittenBookItem || player.getStackInHand(Hand.OFF_HAND).getItem() instanceof WritableBookItem)
				AudiobookLogic.playBook(player.getStackInHand(Hand.OFF_HAND));
		} else if(Audiobook.stopAudiobookKey.isPressed())
			AudiobookLogic.stopNarration();
	}
}
