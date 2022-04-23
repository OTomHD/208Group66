package com.group66.tummm.items.custom;

import com.group66.tummm.blocks.tummmBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class PlatinumScanner extends Item {
    public PlatinumScanner(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY(); i++) {
                Block blockBelow = context.getWorld().getBlockState(positionClicked.down(i)).getBlock();

                if(isPlatinum(blockBelow)) {
                    outputPlatinumFound(player);
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(new LiteralText("No platinum found"), false);
            }
        }

        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }

    private void outputPlatinumFound(PlayerEntity player) {
        player.sendMessage(new LiteralText("Found platinum underground!"), false);
    }

    private boolean isPlatinum(Block block) {
        return block == tummmBlocks.PLATINUM_ORE || block == tummmBlocks.DEEPSLATE_PLATINUM;
    }
}
