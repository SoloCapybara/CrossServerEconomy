package com.solocapybara.servereconomy.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import com.solocapybara.servereconomy.world.inventory.AtmContainer;
// import com.solocapybara.servereconomy.Registration; // 如果不再需要 MOD_ID，可以移除

// 导入按钮类
import net.minecraft.client.gui.components.Button;


public class AtmScreen extends AbstractContainerScreen<AtmContainer> {
    // 不再需要自定义 GUI 纹理
    // private static final ResourceLocation TEXTURE = new ResourceLocation(Registration.MOD_ID, "textures/gui/atm_gui.png");

    public AtmScreen(AtmContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
        // GUI 尺寸可以根据需要调整，或者不设置，让内容决定
        // this.imageWidth = 176; // 示例宽度
        // this.imageHeight = 166; // 示例高度

        // 标题位置相对于 GUI 窗口左上角 (leftPos, topPos)
        this.titleLabelX = this.imageWidth / 2; // 设置标题居中，如果设置了 imageWidth
        this.titleLabelY = 6; // 设置标题 Y 坐标
    }

    @Override
    protected void init() {
        super.init();
        // leftPos 和 topPos 在 super.init() 中计算

        // 查询余额按钮
        this.addRenderableWidget(Button.builder(Component.literal("查询余额"), (button) -> {
            System.out.println("查询余额按钮被点击！");
        }).pos(this.leftPos + 10, this.topPos + 30).size(100, 20).build());

        // 存入余额按钮
        this.addRenderableWidget(Button.builder(Component.literal("存入余额"), (button) -> {
            System.out.println("存入余额按钮被点击！");
        }).pos(this.leftPos + 10, this.topPos + 60).size(100, 20).build());

        // 取出余额按钮
        this.addRenderableWidget(Button.builder(Component.literal("取出余额"), (button) -> {
            System.out.println("取出余额按钮被点击！");
        }).pos(this.leftPos + 10, this.topPos + 90).size(100, 20).build());

        // 关闭按钮
        this.addRenderableWidget(Button.builder(Component.literal("关闭"), (button) -> {
            this.onClose();
        }).pos(this.leftPos + 120, this.topPos + 30).size(40, 20).build());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        // 绘制半透明灰色背景遮罩
        // 使用 fill 方法绘制一个矩形，颜色为半透明灰色 (ARGB)
        int backgroundColor = 0x80101010; // 半透明灰色，ARGB格式
        guiGraphics.fill(0, 0, this.width, this.height, backgroundColor);

        // 不再绘制自定义纹理背景
        // RenderSystem.setShader(GameRenderer::getPositionTexShader);
        // RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        // RenderSystem.setShaderTexture(0, TEXTURE);
        // int x = (this.width - this.imageWidth) / 2;
        // int y = (this.height - this.imageHeight) / 2;
        // guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // 不需要 renderBackground(guiGraphics); 因为我们在 renderBg 中绘制了全屏遮罩
        super.render(guiGraphics, mouseX, mouseY, partialTicks); // 渲染按钮等组件
        renderTooltip(guiGraphics, mouseX,mouseY); // 渲染物品悬停提示
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // 绘制 GUI 标题
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false); // GUI 标题
        // 显示余额（这里用假数据，实际应从容器/玩家数据获取）
        guiGraphics.drawString(this.font, "余额: 12345", 10, 120, 0xFFFFFF, false);
        // 不需要玩家背包标题，除非你在 GUI 中显示了玩家背包
        // guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, 4210752, false); // 玩家背包标题，如果需要
    }

    // TODO: 添加按钮和其他交互元素的处理方法

} 