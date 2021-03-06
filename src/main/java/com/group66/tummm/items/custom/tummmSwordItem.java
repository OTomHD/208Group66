package com.group66.tummm.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class tummmSwordItem extends SwordItem {
    public tummmSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200, 2), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 2), attacker);
        return super.postHit(stack, target, attacker);
    }
}
