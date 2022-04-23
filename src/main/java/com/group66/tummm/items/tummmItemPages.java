package com.group66.tummm.items;

import com.group66.tummm.MainTummm;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.Identifier;

public class tummmItemPages {

    public static final ItemGroup BLOCKS = FabricItemGroupBuilder.build
            (new Identifier(MainTummm.MODID,"blocks"), () -> new ItemStack(tummmItems.RAW_PLATINUM));

    public static final ItemGroup ITEMS = FabricItemGroupBuilder.build
            (new Identifier(MainTummm.MODID,"items"), () -> new ItemStack(tummmItems.INFUSED_DIAMOND));

    public static final ItemGroup TOOLS = FabricItemGroupBuilder.build
            (new Identifier(MainTummm.MODID,"tools"), () -> new ItemStack(tummmItems.PLATINUM_AXE));

    public static final ItemGroup CRYSTALS = FabricItemGroupBuilder.build
            (new Identifier(MainTummm.MODID,"crystals"), () -> new ItemStack(tummmItems.MANA_CRYSYTAL));
}
