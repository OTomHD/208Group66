package com.group66.tummm.items.custom.wands;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class tummmWandLightning extends tummmWand{
    public tummmWandLightning(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(hasMana(context.getPlayer(),150)){
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT,context.getWorld());
            lightning.setPos(context.getBlockPos().getX(),
                    context.getBlockPos().getY()+1,
                    context.getBlockPos().getZ());
            context.getWorld().spawnEntity(lightning);
        }
        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.tummm.lightning_wand.tooltip").formatted(Formatting.LIGHT_PURPLE));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
