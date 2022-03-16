package com.group66.tummm.items;

import com.group66.tummm.MainTummm;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.Identifier;

public class tummmItemPages {

    public static final ItemGroup MAIN = FabricItemGroupBuilder.build
            (new Identifier(MainTummm.MODID,"main"), () -> new ItemStack(MinecartItem.byRawId(64)));

}
