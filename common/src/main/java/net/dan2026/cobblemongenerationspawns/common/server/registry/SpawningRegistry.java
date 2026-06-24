/*
 *
 * Cobblemon: Generation Spawning - A NeoForge Minecraft Mod.
 *
 * Copyright (c) 2026 DAN2026. All rights reserved.
 *
 * This software is licensed under the CobblemonGenerationSpawning License v1.0.
 *  A copy of this License should have been included with this software.
 *  If not, you can obtain a copy at [https://github.com/DAN2026/CobblemonGenerationSpawning/blob/master/LICENSE].
 */

package net.dan2026.cobblemongenerationspawns.common.server.registry;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.spawning.BestSpawner;
import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import net.dan2026.cobblemongenerationspawns.common.server.spawns.SpawnFactors;
import net.minecraft.server.MinecraftServer;

public class SpawningRegistry {

    /**
     * Registers the spawning influences and initializes the cache for all server levels.
     *
     * @param server The server instance.
     */

    public static void register(MinecraftServer server) {

        Runnable registerLogic = () -> {

            final SpawnFactors spawnFactors = new SpawnFactors();

            PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(serverPlayer -> spawnFactors);

            var fishingSpawner = BestSpawner.INSTANCE.getFishingSpawner();

            fishingSpawner.getInfluences().add(spawnFactors);

            Cobblemon.LOGGER.info("Generation Spawns: Registered");

        };

        try{
            registerLogic.run();
        }catch (Exception exception){
            Cobblemon.LOGGER.info("Generation Spawns: Waiting for Cobblemon to initialise.");
        }

    }

}