package com.group66.tummm.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

public class tummmPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> PLATINUM_ORE_PLACED =
            PlacedFeatures.register("platinum_ore_placed", tummmConfiguredFeatures.PLATINUM_ORE,
                    tummmOreFeatures.modifiersWithCount(7,
                            HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    public static final RegistryEntry<PlacedFeature> MAGIC_ORE_PLACED =
            PlacedFeatures.register("magic_ore_placed", tummmConfiguredFeatures.MAGIC_ORE,
                    tummmOreFeatures.modifiersWithCount(9,
                            HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-60), YOffset.aboveBottom(60))));

    public static final RegistryEntry<PlacedFeature> ANGELIC_FLOWER_PLACED =
            PlacedFeatures.register("angelic_flower_placed", tummmConfiguredFeatures.ANGELIC_FLOWER,
                    RarityFilterPlacementModifier.of(20), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BiomePlacementModifier.of());
}
