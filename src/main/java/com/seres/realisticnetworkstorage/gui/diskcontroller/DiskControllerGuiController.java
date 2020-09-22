package com.seres.realisticnetworkstorage.gui.diskcontroller;

import com.seres.realisticnetworkstorage.RealisticNetworkStorage;
import com.seres.realisticnetworkstorage.gui.RNSScreens;
import com.seres.realisticnetworkstorage.items.disks.BaseDiskItem;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public class DiskControllerGuiController extends SyncedGuiDescription
{
    public DiskControllerGuiController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(RNSScreens.diskControllerScreen, syncId, playerInventory, getBlockInventory(context, 4), getBlockPropertyDelegate(context, 2));
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        Texture bg = new Texture(new Identifier(RealisticNetworkStorage.MODID, "textures/gui/bar.png"));
        Texture b = new Texture(new Identifier(RealisticNetworkStorage.MODID, "textures/gui/bar_mark.png"));
        WBar bar = new WBar(bg, b, 0, 1, WBar.Direction.UP);
        bar.withTooltip("gui.realisticnetworkstorage.energy_generator.tooltip");
        bar.validate(this);
        root.add(bar, 8, 1, 1, 3);
        WItemSlot slot = WItemSlot.of(blockInventory, 0);
        WItemSlot slot1 = WItemSlot.of(blockInventory, 1);
        WItemSlot slot2 = WItemSlot.of(blockInventory, 2);
        WItemSlot slot3 = WItemSlot.of(blockInventory, 3);
        Predicate<ItemStack> predicate = itemStack -> itemStack.getItem() instanceof BaseDiskItem && itemStack.getCount() == 1;
        slot.setFilter(predicate);
        slot1.setFilter(predicate);
        slot2.setFilter(predicate);
        slot3.setFilter(predicate);
        root.add(slot, 0, 1);
        root.add(slot1, 0, 3);
        root.add(slot2, 6, 1);
        root.add(slot3, 6, 3);
        root.add(this.createPlayerInventoryPanel(), 0, 4);
        root.setSize(100, 100);
        root.validate(this);
    }
}
