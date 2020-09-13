package the_fireplace.audiobook;

import com.google.common.collect.Lists;
import com.mojang.text2speech.Narrator;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class AudiobookLogic {
    private static boolean playing = false;
    public static void playPauseBook(ItemStack stack) {
        playPauseBook(stack.getTag() != null ? BookScreen.readPages(stack.getTag()) : null);
    }

    public static void playPauseBook(BookScreen.Contents contents) {
        List<String> pages = Lists.newArrayListWithCapacity(contents.getPageCount());
        for(int i=0;i<contents.getPageCount();i++)
            pages.add(contents.getPage(i).getString());
        playPauseBook(pages);
    }

    public static void playPauseBook(@Nullable List<String> pages) {
        if(playing && Narrator.getNarrator().active()) {
            playing = false;
            Narrator.getNarrator().clear();
        } else if(pages != null && !pages.isEmpty()) {
            playing = true;
            StringBuilder output = new StringBuilder();
            for(String page: pages)
                output.append(" ").append(page);
            Narrator.getNarrator().say(output.toString(), true);
        }
    }
}
