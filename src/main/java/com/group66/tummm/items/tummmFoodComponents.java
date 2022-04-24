package com.group66.tummm.items;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class tummmFoodComponents {
    public static final FoodComponent ANGELIC_APPLE = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).statusEffect(
            new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0f).statusEffect(
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 1000, 1), 1.0f).statusEffect(
            new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1000, 1), 1.0f).build();


}
