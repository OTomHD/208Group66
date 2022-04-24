package com.group66.tummm;

import com.group66.tummm.blocks.tummmBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class tummmClientMod  implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(tummmBlocks.ANGELIC_FLOWER, RenderLayer.getCutout());
    }
}
