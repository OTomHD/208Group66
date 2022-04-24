package com.group66.tummm.items.custom.wands;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class tummmWandHealth extends tummmWand{
    public tummmWandHealth(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(hasMana(user,150)){
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 1), user);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.tummm.regen_wand.tooltip").formatted(Formatting.LIGHT_PURPLE));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
