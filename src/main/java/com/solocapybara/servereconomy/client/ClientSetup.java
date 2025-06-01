package com.solocapybara.servereconomy.client;

import com.solocapybara.servereconomy.Registration;
import com.solocapybara.servereconomy.client.gui.screen.AtmScreen;
import com.solocapybara.servereconomy.world.inventory.AtmContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "server_economy", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.ATM_CONTAINER.get(), AtmScreen::new);
        });
    }
}