package com.group66.tummm.items.custom;

import com.group66.tummm.items.tummmItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static net.minecraft.util.Hand.MAIN_HAND;

public class tummmManaCrystal extends Item {
    public tummmManaCrystal(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(stack.getItem() == tummmItems.MANA_CRYSYTAL) {
            if(entity instanceof PlayerEntity) {
                int rand = (int)Math.floor(Math.random()*(10-0+1)+0);
                if (rand == 3){
                    stack.damage(-1,((PlayerEntity) entity),(p -> p.sendToolBreakStatus(MAIN_HAND)));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
