package dev.the_fireplace.audiobook;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.text2speech.Narrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WritableBookItem;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.component.WritableBookContent;
import net.minecraft.world.item.component.WrittenBookContent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public final class AudiobookLogic
{
    public static void playBook(ItemStack stack) {
        playBook(stack.getTags().toArray().length > 0 ? BookViewScreen.BookAccess.fromItem(stack) : null);
    }

    public static void playBook(@Nullable BookViewScreen.BookAccess contents) {
        if (contents != null) {
            List<String> pages = Lists.newArrayListWithCapacity(contents.getPageCount());
            for (int i = 0; i < contents.getPageCount(); i++) {
                pages.add(contents.getPage(i).getString());
            }
            playBook(pages);
        }
    }

    public static void playBook(@Nullable List<String> pages) {
        if (pages != null && !pages.isEmpty()) {
            StringBuilder output = new StringBuilder();
            for (String page : pages) {
                output.append(" ").append(page);
            }
            Minecraft minecraft = Minecraft.getInstance();
            float volume = minecraft.options.getSoundSourceVolume(SoundSource.VOICE) * minecraft.options.getSoundSourceVolume(SoundSource.MASTER);
            Narrator.getNarrator().say(output.toString(), true, volume);
        }
    }

    public static void stopNarration() {
        Narrator.getNarrator().clear();
    }

    public static boolean isReadableBook(ItemStack stack) {
        return isReadable(stack) && isBook(stack);
    }

    private static boolean isReadable(ItemStack stack) {
        return !readPages(stack).isEmpty();
    }

    private static List<String> readPages(ItemStack stack) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        Objects.requireNonNull(builder);
        WrittenBookContent writtenBookContent = stack.get(DataComponents.WRITTEN_BOOK_CONTENT);
        if (writtenBookContent != null) {
            builder.addAll(writtenBookContent.getPages(false).stream().map((component) -> component.plainCopy().getString()).toList());
        }
        WritableBookContent writableBookContent = stack.get(DataComponents.WRITABLE_BOOK_CONTENT);
        if (writableBookContent != null) {
            builder.addAll(writableBookContent.getPages(false).toList());
        }
        return builder.build();
    }

    private static boolean isBook(ItemStack stack) {
        return stack.getItem() instanceof WrittenBookItem || stack.getItem() instanceof WritableBookItem;
    }
}
