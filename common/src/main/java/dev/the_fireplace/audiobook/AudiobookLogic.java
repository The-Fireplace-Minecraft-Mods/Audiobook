package dev.the_fireplace.audiobook;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.text2speech.Narrator;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WritableBookItem;
import net.minecraft.world.item.WrittenBookItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public final class AudiobookLogic
{
    public static void playBook(ItemStack stack) {
        playBook(stack.hasTag() && stack.getTag() != null ? BookViewScreen.BookAccess.fromItem(stack) : null);
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
            Narrator.getNarrator().say(output.toString(), true);
        }
    }

    public static void stopNarration() {
        Narrator.getNarrator().clear();
    }

    public static boolean isReadableBook(ItemStack stack) {
        return isReadable(stack) && isBook(stack);
    }

    private static boolean isReadable(ItemStack stack) {
        if (!stack.hasTag()) {
            return false;
        }
        assert stack.getTag() != null;

        return !readPages(stack.getTag()).isEmpty();
    }

    private static List<String> readPages(CompoundTag nbt) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        Objects.requireNonNull(builder);
        BookViewScreen.loadPages(nbt, builder::add);
        return builder.build();
    }

    private static boolean isBook(ItemStack stack) {
        return stack.getItem() instanceof WrittenBookItem || stack.getItem() instanceof WritableBookItem;
    }
}
