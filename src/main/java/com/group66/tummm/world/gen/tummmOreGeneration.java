package com.group66.tummm.world.gen;

import com.group66.tummm.world.feature.tummmPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class tummmOreGeneration {
    public static void  generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, tummmPlacedFeatures.PLATINUM_ORE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, tummmPlacedFeatures.MAGIC_ORE_PLACED.getKey().get());
    }
}
