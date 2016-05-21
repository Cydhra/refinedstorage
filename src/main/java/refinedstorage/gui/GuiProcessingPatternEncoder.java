package refinedstorage.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import refinedstorage.RefinedStorage;
import refinedstorage.container.ContainerProcessingPatternEncoder;
import refinedstorage.network.MessageGridPatternCreate;
import refinedstorage.tile.autocrafting.TileProcessingPatternEncoder;

import java.io.IOException;

public class GuiProcessingPatternEncoder extends GuiBase {
    private TileProcessingPatternEncoder ppEncoder;

    public GuiProcessingPatternEncoder(ContainerProcessingPatternEncoder container, TileProcessingPatternEncoder ppEncoder) {
        super(container, 176, 172);

        this.ppEncoder = ppEncoder;
    }

    @Override
    public void init(int x, int y) {
    }

    @Override
    public void update(int x, int y) {
    }

    public boolean isHoveringOverCreatePattern(int mouseX, int mouseY) {
        return inBounds(152, 38, 16, 16, mouseX, mouseY) && ppEncoder.mayCreatePattern();
    }

    @Override
    public void drawBackground(int x, int y, int mouseX, int mouseY) {
        bindTexture("gui/processing_pattern_encoder.png");

        drawTexture(x, y, 0, 0, width, height);

        int ty = 0;

        if (isHoveringOverCreatePattern(mouseX - guiLeft, mouseY - guiTop)) {
            ty = 1;
        }

        if (!ppEncoder.mayCreatePattern()) {
            ty = 2;
        }

        drawTexture(x + 152, y + 38, 178, ty * 16, 16, 16);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        drawString(7, 7, t("gui.refinedstorage:processing_pattern_encoder"));
        drawString(7, 78, t("container.inventory"));

        if (isHoveringOverCreatePattern(mouseX, mouseY)) {
            drawTooltip(mouseX, mouseY, t("gui.refinedstorage:processing_pattern_encoder.pattern_create"));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (isHoveringOverCreatePattern(mouseX - guiLeft, mouseY - guiTop)) {
            RefinedStorage.NETWORK.sendToServer(new MessageGridPatternCreate(ppEncoder.getPos().getX(), ppEncoder.getPos().getY(), ppEncoder.getPos().getZ()));

            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.ui_button_click, 1.0F));
        }
    }
}
