package com.group66.tummm.world.feature;

import com.group66.tummm.MainTummm;
import com.group66.tummm.blocks.tummmBlocks;
import net.minecraft.world.gen.feature.*;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class tummmConfiguredFeatures {

    public static final List<OreFeatureConfig.Target> OVERWORLD_PLATINUM_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                tummmBlocks.PLATINUM_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    tummmBlocks.DEEPSLATE_PLATINUM.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PLATINUM_ORE =
            ConfiguredFeatures.register("platinum_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_PLATINUM_ORES, 7));

    public static final List<OreFeatureConfig.Target> OVERWORLD_MAGIC_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    tummmBlocks.MAGIC_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    tummmBlocks.DEEPSLATE_MAGIC.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> MAGIC_ORE =
            ConfiguredFeatures.register("magic_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_MAGIC_ORES, 9));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ANGELIC_FLOWER =
            ConfiguredFeatures.register("angelic_flower", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(32, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(tummmBlocks.ANGELIC_FLOWER)))));

    public static void registerConfiguredFeatures() {
        MainTummm.LOGGER.info("Registering tummm configured features for" + MainTummm.MODID);
    }
}
