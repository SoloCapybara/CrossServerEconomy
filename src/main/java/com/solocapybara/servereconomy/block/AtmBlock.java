package com.solocapybara.servereconomy.block;

import com.solocapybara.servereconomy.world.inventory.AtmContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;

import org.jetbrains.annotations.Nullable;

public class AtmBlock extends Block {

    public AtmBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) { // 确保只在服务器端执行逻辑
            if (player instanceof ServerPlayer serverPlayer) {
                // 打开 GUI
                NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("ATM");
                    }

                    @Nullable
                    @Override
                    public AtmContainer createMenu(int windowId, net.minecraft.world.entity.player.Inventory inventory, Player player) {
                        return new AtmContainer(windowId, inventory, pos); // 将方块位置传递给容器
                    }
                }, pos); // 将方块位置传递给 NetworkHooks 以便在客户端读取
            }
        }
        return InteractionResult.SUCCESS; // 返回成功，表示事件已处理
    }
} 