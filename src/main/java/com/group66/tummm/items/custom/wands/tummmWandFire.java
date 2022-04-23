package com.group66.tummm.items.custom.wands;


import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class tummmWandFire extends tummmWand{
    public tummmWandFire(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(hasMana(context.getPlayer(),150)){
            FireballEntity fireball = new FireballEntity(EntityType.FIREBALL,context.getWorld());
            fireball.setPos(context.getBlockPos().getX(),
                    context.getBlockPos().getY()+2,
                    context.getBlockPos().getZ());
            context.getWorld().spawnEntity(fireball);
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.tummm.fire_wand.tooltip").formatted(Formatting.LIGHT_PURPLE));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
