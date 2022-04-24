package com.group66.tummm.items;

import com.group66.tummm.MainTummm;
import com.group66.tummm.items.custom.*;
import com.group66.tummm.items.custom.wands.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;


public class tummmItems {

    public static final Item MANA_CRYSYTAL = registerItem("mana_crystal",
                    new tummmManaCrystal(new FabricItemSettings().group(tummmItemPages.CRYSTALS).maxCount(1).maxDamage(301).rarity(Rarity.UNCOMMON)));

    public static final Item MANA_CRYSYTAL2 = registerItem("mana_crystal_lvl2",
            new tummmManaCrystal(new FabricItemSettings().group(tummmItemPages.CRYSTALS).fireproof().maxCount(1).maxDamage(601).rarity(Rarity.RARE)));

    public static final Item MANA_CRYSYTAL3 = registerItem("mana_crystal_lvl3",
            new tummmManaCrystal(new FabricItemSettings().group(tummmItemPages.CRYSTALS).fireproof().maxCount(1).maxDamage(1201).rarity(Rarity.EPIC)));

    public static final Item WAND_VOID = registerItem("void_wand",
            new tummmWandVoid(new FabricItemSettings().group(tummmItemPages.TOOLS).maxCount(1)));

    public static final Item WAND_CLOAK = registerItem("cloak_wand",
            new tummmWandCloak(new FabricItemSettings().group(tummmItemPages.TOOLS).maxCount(1)));

    public static final Item WAND_FIRE = registerItem("fire_wand",
            new tummmWandFire(new FabricItemSettings().group(tummmItemPages.TOOLS).maxCount(1)));

    public static final Item WAND_EXP = registerItem("exp_wand",
            new tummmWandExp(new FabricItemSettings().group(tummmItemPages.TOOLS).maxCount(1)));

    public static final Item WAND_LIGHTNING = registerItem("lightning_wand",
            new tummmWandLightning(new FabricItemSettings().group(tummmItemPages.TOOLS).maxCount(1)));

    public static final Item PLATINUM_INGOT = registerItem("platinum_ingot",
            new Item(new FabricItemSettings().group(tummmItemPages.ITEMS)));

    public static final Item PLATINUM_NUGGET = registerItem("platinum_nugget",
            new Item(new FabricItemSettings().group(tummmItemPages.ITEMS)));

    public static final Item RAW_PLATINUM = registerItem("raw_platinum",
            new Item(new FabricItemSettings().group(tummmItemPages.ITEMS)));


    public static final Item INFUSED_DIAMOND = registerItem("infused_diamond",
            new Item(new FabricItemSettings().group(tummmItemPages.ITEMS)));

    public static final Item MANA_DUST = registerItem("mana_dust",
            new Item(new FabricItemSettings().group(tummmItemPages.ITEMS)));

    public static final Item PLATINUM_PICKAXE = registerItem("platinum_pickaxe",
            new tummmPickaxeItem(tummmToolMaterials.PLATINUM, 1,0f,
                new FabricItemSettings().group(tummmItemPages.TOOLS)));

    public static final Item PLATINUM_AXE = registerItem("platinum_axe",
            new tummmAxeItem(tummmToolMaterials.PLATINUM, 3,1f,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));

    public static final Item PLATINUM_HOE = registerItem("platinum_hoe",
            new tummmHoeItem(tummmToolMaterials.PLATINUM, 0,0f,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));

    public static final Item PLATINUM_SHOVEL = registerItem("platinum_shovel",
            new tummmShovelItem(tummmToolMaterials.PLATINUM, 1,1f,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));

    public static final Item PLATINUM_SWORD = registerItem("platinum_sword",
            new tummmSwordItem(tummmToolMaterials.PLATINUM, 2,1.5f,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));

    public static final Item PLATINUM_HELMET = registerItem("platinum_helmet",
            new ArmorItem(ModArmorMaterials.PLATINUM, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));
    public static final Item PLATINUM_CHESTPLATE = registerItem("platinum_chestplate",
            new ArmorItem(ModArmorMaterials.PLATINUM, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));
    public static final Item PLATINUM_LEGGINS = registerItem("platinum_leggings",
            new ArmorItem(ModArmorMaterials.PLATINUM, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));
    public static final Item PLATINUM_BOOTS = registerItem("platinum_boots",
            new ArmorItem(ModArmorMaterials.PLATINUM, EquipmentSlot.FEET,
                    new FabricItemSettings().group(tummmItemPages.TOOLS)));


    public static final Item PLATINUM_SCANNER = registerItem("platinum_scanner",
            new PlatinumScanner(new FabricItemSettings().group(tummmItemPages.TOOLS)));

    public static final Item ANGELIC_APPLE = registerItem("angelic_apple",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(tummmFoodComponents.ANGELIC_APPLE)));

    public static final Item MAGIC_PAGE = registerItem("magic_page",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(MainTummm.MODID, name),item);
    }
    public static void registerModItems(){
        MainTummm.LOGGER.info("["+MainTummm.MODID+"]"+"Mod Item Creation...");
    }

}
