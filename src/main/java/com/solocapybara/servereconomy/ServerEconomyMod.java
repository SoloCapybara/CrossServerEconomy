package com.solocapybara.servereconomy;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match your modid
@Mod(Registration.MOD_ID)
public class ServerEconomyMod {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ServerEconomyMod() {
        LOGGER.info("HELLO from ServerEconomyMod loading!");
        // 获取模组事件总线
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // 注册物品和方块
        Registration.register(modEventBus);
        // 注册创造模式标签页
        Registration.CREATIVE_MODE_TABS.register(modEventBus);
    }

    // 如果需要监听其他事件，可以在这里添加更多使用@SubscribeEvent注解的方法
    // 例如，监听RegistryEvent.Register事件来注册更多内容

    // @SubscribeEvent
    // public static void onRegisterItems(RegistryEvent.Register<Item> event) {
    //     // 在这里注册物品
    // }
} 