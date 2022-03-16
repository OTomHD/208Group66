package com.group66.tummm.items;

import com.group66.tummm.MainTummm;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class tummmItems {

    public static final Item MANA_CRYSYTAL = registerItem
            ("mana_crystal",
                    new Item(new FabricItemSettings().group(tummmItemPages.MAIN).fireproof().maxCount(1)));




    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(MainTummm.MODID, name),item);
    }
    public static void registerModItems(){
        MainTummm.LOGGER.info("["+MainTummm.MODID+"]"+"Mod Item Creation...");
    }

}
