package io.github.sonopsa.explytranerf;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExperimentalElytraNerf implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("experimental-elytra-nerf");
	public static final GameRules.Key<GameRules.IntRule> ELYTRA_FIREWORK_DURABILITY = GameRuleRegistry.
			register("elytraFireworkDurability", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(3, 0));
	public static final GameRules.Key<GameRules.IntRule> ELYTRA_FLIGHT_DURABILITY_RATE = GameRuleRegistry.
			register("elytraFlightDurabilityRate", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(0, 0));

	@Override
	public void onInitialize() {
	}
}