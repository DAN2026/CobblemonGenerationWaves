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

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import net.dan2026.cobblemongenerationspawns.common.server.spawns.SpawnFactors;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class SpawningRegistry {

    /**
     * Registers the spawning influences and initializes the cache for all server levels.
     *
     * @param server The server instance.
     */

    public static void register(MinecraftServer server) {

        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(SpawningRegistry::initializeSpawnFactor);

    }

    /**
     * Creates a new spawn factor.
     *
     * @param serverPlayer The player the spawner is affecting.
     * @return SpawnFactors
     * @see SpawnFactors
     */

    private static SpawnFactors initializeSpawnFactor(ServerPlayer serverPlayer) {
        return new SpawnFactors();
    }

}