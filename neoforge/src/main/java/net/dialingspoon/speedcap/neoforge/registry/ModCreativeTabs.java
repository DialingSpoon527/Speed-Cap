package net.dialingspoon.speedcap.neoforge.registry;

import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpeedCap.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPEEDCAP_TAB = CREATIVE_MODE_TABS.register("speedcap_tab", () -> CreativeModeTab.builder().icon(() -> createColoredCap(DyeColor.BLUE))
            .title(Component.translatable("creativetab.speedcap_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(new ItemStack(ModItems.SPEEDCAP.get()));
                pOutput.accept(createColoredCap(DyeColor.LIGHT_GRAY));
                pOutput.accept(createColoredCap(DyeColor.GRAY));
                pOutput.accept(createColoredCap(DyeColor.BLACK));
                pOutput.accept(createColoredCap(DyeColor.BROWN));
                pOutput.accept(createColoredCap(DyeColor.RED));
                pOutput.accept(createColoredCap(DyeColor.ORANGE));
                pOutput.accept(createColoredCap(DyeColor.YELLOW));
                pOutput.accept(createColoredCap(DyeColor.LIME));
                pOutput.accept(createColoredCap(DyeColor.GREEN));
                pOutput.accept(createColoredCap(DyeColor.CYAN));
                pOutput.accept(createColoredCap(DyeColor.LIGHT_BLUE));
                pOutput.accept(createColoredCap(DyeColor.BLUE));
                pOutput.accept(createColoredCap(DyeColor.PURPLE));
                pOutput.accept(createColoredCap(DyeColor.MAGENTA));
                pOutput.accept(createColoredCap(DyeColor.PINK));
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    private static ItemStack createColoredCap(DyeColor color) {
        ItemStack cap = new ItemStack(ModItems.SPEEDCAP.get());
        int colorhash = color.getTextureDiffuseColor();

        cap.set(DataComponents.DYED_COLOR, new DyedItemColor(colorhash));
        return cap;
    }
}
