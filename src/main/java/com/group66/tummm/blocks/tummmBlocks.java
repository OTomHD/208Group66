package com.group66.tummm.blocks;

import com.group66.tummm.MainTummm;
import com.group66.tummm.items.tummmItemPages;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.data.Main;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class tummmBlocks {

    public static final Block PLATINUM_ORE = registerBlock("platinum_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()), tummmItemPages.BLOCKS);

    public static final Block DEEPSLATE_PLATINUM = registerBlock("deepslate_platinum",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()), tummmItemPages.BLOCKS);

    public static final Block MAGIC_ORE = registerBlock("magic_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(2f).requiresTool()), tummmItemPages.BLOCKS);

    public static final Block DEEPSLATE_MAGIC = registerBlock("deepslate_magic",
            new Block(FabricBlockSettings.of(Material.STONE).strength(2f).requiresTool()), tummmItemPages.BLOCKS);

    public static final Block RITUAL_CORE = registerBlock("ritual_core",
            new ritual_core(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), tummmItemPages.BLOCKS);

    public static final Block ANGELIC_FLOWER = registerBlock("angelic_flower",
            new FlowerBlock(StatusEffects.REGENERATION, 10, FabricBlockSettings.copy(Blocks.DANDELION)
                    .strength(1f).nonOpaque()), tummmItemPages.BLOCKS);

    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(MainTummm.MODID, name), block);
    }

    private  static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(MainTummm.MODID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }
    public static void registerModBlocks() {
        MainTummm.LOGGER.info("Registering tummm blocks for" + MainTummm.MODID);
    }
}
