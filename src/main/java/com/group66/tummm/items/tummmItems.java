package com.group66.tummm.items;

import com.group66.tummm.MainTummm;
import com.group66.tummm.items.custom.tummmAxeItem;
import com.group66.tummm.items.custom.tummmHoeItem;
import com.group66.tummm.items.custom.tummmPickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class tummmItems {

    public static final Item MANA_CRYSYTAL = registerItem
            ("mana_crystal",
                    new Item(new FabricItemSettings().group(tummmItemPages.MAIN).fireproof().maxCount(1).maxDamage(300)));

    public static final Item PLATINUM_INGOT = registerItem("platinum_ingot",
            new Item(new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item PLATINUM_NUGGET = registerItem("platinum_nugget",
            new Item(new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item RAW_PLATINUM = registerItem("raw_platinum",
            new Item(new FabricItemSettings().group(tummmItemPages.MAIN)));


    public static final Item INFUSED_DIAMOND = registerItem("infused_diamond",
            new Item(new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item MANA_DUST = registerItem("mana_dust",
            new Item(new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item PLATINUM_PICKAXE = registerItem("platinum_pickaxe",
            new tummmPickaxeItem(tummmToolMaterials.PLATINUM, 1,0f,
                new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item PLATINUM_AXE = registerItem("platinum_axe",
            new tummmAxeItem(tummmToolMaterials.PLATINUM, 3,1f,
                    new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item PLATINUM_HOE = registerItem("platinum_hoe",
            new tummmHoeItem(tummmToolMaterials.PLATINUM, 0,0f,
                    new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item PLATINUM_SHOVEL = registerItem("platinum_shovel",
            new ShovelItem(tummmToolMaterials.PLATINUM, 1,1f,
                    new FabricItemSettings().group(tummmItemPages.MAIN)));

    public static final Item PLATINUM_SWORD = registerItem("platinum_sword",
            new SwordItem(tummmToolMaterials.PLATINUM, 2,2f,
                    new FabricItemSettings().group(tummmItemPages.MAIN)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(MainTummm.MODID, name),item);
    }
    public static void registerModItems(){
        MainTummm.LOGGER.info("["+MainTummm.MODID+"]"+"Mod Item Creation...");
    }

}
