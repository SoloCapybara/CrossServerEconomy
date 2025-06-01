package com.solocapybara.servereconomy.world.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import com.solocapybara.servereconomy.Registration;

// 实现 IContainerFactory 用于从 FriendlyByteBuf 创建容器
public class AtmContainer extends AbstractContainerMenu implements IContainerFactory<AtmContainer> {

    private final Level level;
    private final BlockPos pos;

    // 服务器端构造函数
    public AtmContainer(int windowId, Inventory playerInventory, BlockPos pos) {
        super(Registration.ATM_CONTAINER.get(), windowId);
        this.level = playerInventory.player.level();
        this.pos = pos;
        // TODO: 添加玩家库存槽位（如果需要显示玩家背包）
        // layoutPlayerInventory(playerInventory);
    }

    // 客户端构造函数，从 FriendlyByteBuf 读取数据
    public AtmContainer(int windowId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(windowId, playerInventory, extraData.readBlockPos());
    }

    // MenuProvider 接口方法，用于在服务器端创建容器

    public Component getDisplayName() {
        return Component.literal("ATM"); // GUI 标题
    }

    // IContainerFactory 接口方法，用于从 FriendlyByteBuf 创建容器（无需 @Override）
    public AtmContainer create(int windowId, Inventory inv, FriendlyByteBuf extraData) {
        return new AtmContainer(windowId, inv, extraData);
    }

    // 当玩家与容器交互时调用
    @Override
    public boolean stillValid(Player pPlayer) {
        // 检查玩家是否在方块附近
        return stillValid(ContainerLevelAccess.create(level, pos), pPlayer, Registration.ATM_BLOCK.get());
    }

    // 如果有物品槽，需要实现这个方法来处理 shift 点击等操作
    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
         return ItemStack.EMPTY; // 没有物品槽，返回空物品堆栈
    }

    // 辅助方法：布局玩家库存槽位（可选）
    // private void layoutPlayerInventory(Inventory playerInventory) {
    //     int startX = 8;
    //     int startY = 84;

    //     // Player inventory
    //     for (int row = 0; row < 3; ++row) {
    //         for (int column = 0; column < 9; ++column) {
    //             this.addSlot(new Slot(playerInventory, column + row * 9 + 9, startX + column * 18, startY + row * 18));
    //         }
    //     }

    //     // Hotbar
    //     for (int index = 0; index < 9; ++index) {
    //         this.addSlot(new Slot(playerInventory, index, startX + index * 18, startY + 58));
    //     }
    // }
}