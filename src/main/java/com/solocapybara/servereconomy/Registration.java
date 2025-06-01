package com.solocapybara.servereconomy;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    public static final String MOD_ID = "server_economy";

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // 注册您的ATM方块
    public static final RegistryObject<Block> ATM_BLOCK = BLOCKS.register("atm",
            () -> new Block(Block.Properties.copy(Blocks.IRON_BLOCK))); // 使用铁块的属性作为例子

    // 注册您的ATM方块对应的物品形式
    public static final RegistryObject<Item> ATM_ITEM = ITEMS.register("atm",
            () -> new BlockItem(ATM_BLOCK.get(), new Item.Properties()));

    // 注册创造模式标签页的 DeferredRegister
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // 定义您的创造模式标签页
    public static final RegistryObject<CreativeModeTab> SERVER_ECONOMY_TAB = CREATIVE_MODE_TABS.register("server_economy_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MOD_ID))
            .icon(() -> ATM_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ATM_ITEM.get()); // 将您的 ATM 物品添加到标签页中
            })
            .build());

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
} 