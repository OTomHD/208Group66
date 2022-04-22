package com.group66.tummm.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class tummmPickaxeItem extends PickaxeItem {
    public tummmPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        ItemStack itemStack = context.getStack();
        PlayerEntity playerEntity = context.getPlayer();
        Explosion explosion = world.createExplosion(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 2.0F, Explosion.DestructionType.BREAK);
        if (playerEntity != null) {
            itemStack.damage(1, playerEntity, (p) -> {
                p.sendToolBreakStatus(context.getHand());
            });
        }
        return super.useOnBlock(context);
    }
}
