package net.dialingspoon.speedcap.item;

import dev.architectury.injectables.annotations.PlatformOnly;
import net.dialingspoon.speedcap.PlatformSpecific;
import net.dialingspoon.speedcap.SpeedCap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

public class SpeedCapItem extends Item {
    public static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().durability(11 * 15)
            .component(PlatformSpecific.getDataComponent(), new CapSettingsComponent(4.8f, 4, true, false, true, false, true, true))
            .component(PlatformSpecific.getAnimComponent(), new CapAnimComponent(false, 0))
            .humanoidArmor(ModMaterials.SPEEDCAP, ArmorType.HELMET)
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SpeedCap.MOD_ID, "speed_cap")));
    public static final int DEFAULT_COLOR = -1;

    public SpeedCapItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if(player.isShiftKeyDown()) {
            player.openMenu(new SimpleMenuProvider((i, playerInventory, playerEntity) ->
                    PlatformSpecific.getMenu().create(i, playerInventory), Component.literal("Speed Cap")));
            return InteractionResult.PASS;
        }
        return super.use(level, player, interactionHand);
    }

    /**
     *This @Overrides Item.initializeClient(Consumer<IClientItemExtensions>) through type-erasure shenanigans.
     */
    @PlatformOnly({PlatformOnly.FORGE, "neoforge"})
    public void initializeClient(Consumer<Object> consumer) {
        consumer.accept(PlatformSpecific.itemExtension());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        consumer.accept(Component.translatable("tooltip.speedcap.speed_cap.tooltip"));
        super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
    }
}
