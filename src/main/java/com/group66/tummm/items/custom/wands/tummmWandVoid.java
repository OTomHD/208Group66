package com.group66.tummm.items.custom.wands;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class tummmWandVoid extends tummmWand {
    public tummmWandVoid(Settings settings) {
        super(settings);
    }

    public double x = 0;
    public double y = 0;
    public double z = 0;


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient) {
            this.x = context.getBlockPos().getX();
            this.y = context.getBlockPos().getY()+1;
            this.z = context.getBlockPos().getZ();
        }
        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(hasMana(user,100)){
            entity.teleport(x,y,z,true);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isInSneakingPose()){
            if(hasMana(user, 100)){
                user.teleport(x,y,z,true);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.tummm.void_wand.tooltip").formatted(Formatting.LIGHT_PURPLE));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
