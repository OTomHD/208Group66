package com.group66.tummm;

import com.group66.tummm.blocks.tummmBlocks;
import com.group66.tummm.items.tummmItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTummm implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "tummm";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Tummm Mod starting...");
		//REGISTERS
		tummmItems.registerModItems();
		tummmBlocks.registerModBlocks();
		LOGGER.info("Tummm Mod Finished!");
	}
}