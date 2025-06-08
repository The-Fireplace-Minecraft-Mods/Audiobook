package dev.the_fireplace.audiobook.entrypoints;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("audiobook")
public class Forge
{
    public Forge(FMLJavaModLoadingContext context) {
        // Register as optional on both sides
        context.registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
    }
}