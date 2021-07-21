package the_fireplace.audiobook;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.text2speech.Narrator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WritableBookItem;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.NbtCompound;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public final class AudiobookLogic {
    public static void playBook(ItemStack stack) {
        playBook(stack.hasNbt() && stack.getNbt() != null ? BookScreen.Contents.create(stack) : null);
    }

    public static void playBook(@Nullable BookScreen.Contents contents) {
        if(contents != null) {
            List<String> pages = Lists.newArrayListWithCapacity(contents.getPageCount());
            for (int i = 0; i < contents.getPageCount(); i++)
                pages.add(contents.getPage(i).getString());
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

    public static boolean isReadableBook(ItemStack stack) {
        return isReadable(stack) && isBook(stack);
    }

    private static boolean isReadable(ItemStack stack) {
        if (!stack.hasNbt()) {
            return false;
        }
        assert stack.getNbt() != null;

        return !readPages(stack.getNbt()).isEmpty();
    }

    private static List<String> readPages(NbtCompound nbt) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        Objects.requireNonNull(builder);
        BookScreen.filterPages(nbt, builder::add);
        return builder.build();
    }

    private static boolean isBook(ItemStack stack) {
        return stack.getItem() instanceof WrittenBookItem || stack.getItem() instanceof WritableBookItem;
    }
}
