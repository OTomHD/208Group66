package com.group66.tummm.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class tummmScytheItem extends SwordItem {
    public tummmScytheItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int rand = (int)Math.floor(Math.random()*10);
        if (rand < 1) {
            if (target.getMaxHealth() >= 200) {
                target.damage(DamageSource.MAGIC, 50);
            } else {
                target.kill();
            }
            attacker.heal(100);
        }
        return super.postHit(stack, target, attacker);
    }
}
