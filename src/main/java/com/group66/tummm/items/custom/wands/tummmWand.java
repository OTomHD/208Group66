package com.group66.tummm.items.custom.wands;

import com.group66.tummm.items.custom.tummmManaCrystal;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static net.minecraft.util.Hand.OFF_HAND;

public class tummmWand extends Item {
    public tummmWand(Settings settings) {
        super(settings);
    }

    public boolean hasMana(PlayerEntity player, int manaCost){
        ItemStack stack = player.getStackInHand(OFF_HAND);
        if(stack.getItem() instanceof tummmManaCrystal
                && stack.getMaxDamage()-stack.getDamage() > manaCost+1){
            stack.damage(manaCost,player,(p -> p.sendToolBreakStatus(OFF_HAND)));
            return true;
        }
        return false;
    }

}
