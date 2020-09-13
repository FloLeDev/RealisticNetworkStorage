package com.seres.realisticnetworkstorage.events;

import com.seres.realisticnetworkstorage.RealisticNetworkStorage;
import com.seres.realisticnetworkstorage.blockentities.BasicEnergyStorageBlockEntity;
import com.seres.realisticnetworkstorage.blockentities.BasicGeneratorBlockEntity;
import com.seres.realisticnetworkstorage.blockentities.RNSBlockEntities;
import com.seres.realisticnetworkstorage.blocks.BasicBlock;
import com.seres.realisticnetworkstorage.blocks.BasicEnergyStorage;
import com.seres.realisticnetworkstorage.blocks.BasicGeneratorBlock;
import com.seres.realisticnetworkstorage.blocks.RNSBlocks;
import com.seres.realisticnetworkstorage.energy.EnergyTier;
import com.seres.realisticnetworkstorage.gui.basicenergystorage.BasicEnergyStorageController;
import com.seres.realisticnetworkstorage.gui.basicgenerator.BasicGeneratorController;
import com.seres.realisticnetworkstorage.items.EnergyTransferStick;
import com.seres.realisticnetworkstorage.items.RNSItems;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RNSRegistry
{
    public static ScreenHandlerType<BasicEnergyStorageController> basicEnergyStorageScreen;
    public static ScreenHandlerType<BasicGeneratorController> basicGeneratorScreen;

    public static void registerEverything()
    {
        registerBlocks();
        registerItems();
        registerBlockEntities();
        registerContainers();
    }

    private static void registerBlocks()
    {
        registerBlock(RNSBlocks.BASIC_BLOCK = new BasicBlock(AbstractBlock.Settings.of(Material.STONE)), "basic_block");
        registerBlock(RNSBlocks.BASIC_ENERGY_BLOCK = new BasicEnergyStorage(AbstractBlock.Settings.of(Material.STONE), EnergyTier.MID), "basic_energy_storage");
        registerBlock(RNSBlocks.BASIC_GENERATOR = new BasicGeneratorBlock(AbstractBlock.Settings.of(Material.STONE), EnergyTier.LOW), "basic_generator");
    }

    private static void registerItems()
    {
        registerItem(RNSItems.ENERGY_ITEM = new EnergyTransferStick(new Item.Settings().group(RealisticNetworkStorage.ITEMGROUP), 1000, EnergyTier.HIGH), "energy_transfer_stick");
    }

    private static void registerBlockEntities()
    {
        RNSBlockEntities.BASIC_ENERGY_STORAGE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                RealisticNetworkStorage.MODID + ":basic_energy_block_entity", BlockEntityType.Builder.create(() -> new BasicEnergyStorageBlockEntity(EnergyTier.MID), RNSBlocks.BASIC_ENERGY_BLOCK).build(null));
        RNSBlockEntities.BASIC_GENERATOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, RealisticNetworkStorage.MODID + ":basic_generator_block_entity", BlockEntityType.Builder.create(() -> new BasicGeneratorBlockEntity(EnergyTier.LOW), RNSBlocks.BASIC_GENERATOR).build(null));
    }

    private static void registerContainers()
    {
        basicEnergyStorageScreen = ScreenHandlerRegistry.registerSimple(new Identifier(RealisticNetworkStorage.MODID, "basic_energy_storage"), (id, inv) -> new BasicEnergyStorageController(id, inv, ScreenHandlerContext.EMPTY));
        basicGeneratorScreen = ScreenHandlerRegistry.registerSimple(new Identifier(RealisticNetworkStorage.MODID, "basic_generator"), (id, inv) -> new BasicGeneratorController(id, inv, ScreenHandlerContext.EMPTY));
    }

    public static void registerBlock(Block block, String name)
    {
        Identifier id = new Identifier("realisticnetworkstorage:" + name);
        Item.Settings itemGroup = new Item.Settings().group(RealisticNetworkStorage.ITEMGROUP);
        Registry.register(Registry.BLOCK, id, block);
        BlockItem itemBlock = new BlockItem(block, itemGroup);
        Registry.register(Registry.ITEM, id, itemBlock);
    }

    public static void registerItem(Item item, String name)
    {
        Identifier id = new Identifier("realisticnetworkstorage:" + name);

        Registry.register(Registry.ITEM, id, item);
    }
}
