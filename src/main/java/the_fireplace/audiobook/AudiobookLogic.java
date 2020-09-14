package the_fireplace.audiobook;

import com.google.common.collect.Lists;
import com.mojang.text2speech.Narrator;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class AudiobookLogic {
    public static void playBook(ItemStack stack) {
        playBook(stack.hasTag() && stack.getTag() != null ? BookScreen.Contents.create(stack) : null);
    }

    public static void playBook(@Nullable BookScreen.Contents contents) {
        if(contents != null) {
            List<String> pages = Lists.newArrayListWithCapacity(contents.getLineCount());
            for (int i = 0; i < contents.getLineCount(); i++)
                pages.add(contents.getLine(i).getString());
            playBook(pages);
        }
    }

    public static void playBook(@Nullable List<String> pages) {
        if(pages != null && !pages.isEmpty()) {
            StringBuilder output = new StringBuilder();
            for(String page: pages)
                output.append(" ").append(page);
            Narrator.getNarrator().say(output.toString(), true);
        }
    }

    public static void stopNarration() {
        Narrator.getNarrator().clear();
    }
}
