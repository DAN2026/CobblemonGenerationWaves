/*
 *
 * CobblemonGenerationWaves - A NeoForge Minecraft Mod.
 *
 * Copyright (c) 2026 DAN2026. All rights reserved.
 *
 * This software is licensed under the CobblemonGenerationWaves License v1.0.
 *  A copy of this License should have been included with this software.
 *  If not, you can obtain a copy at [https://github.com/DAN2026/CobblemonGenerationWaves/blob/master/LICENSE].
 */

package net.dan2026.cobblemongenerationwaves.fabric;

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import net.dan2026.cobblemongenerationwaves.common.server.registry.CommandRegistry;
import net.dan2026.cobblemongenerationwaves.common.server.spawns.SpawnFactors;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.level.ServerPlayer;

public class FabricServer implements ModInitializer {

    @Override
    public void onInitialize() {

        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(this::initializeSpawnFactor);

        CommandRegistrationCallback.EVENT.register(
                (dispatcher,
                 registryAccess,
                 environment) -> {
                    CommandRegistry.register(dispatcher);
                });
    }


    /**
     * Creates a new spawn factor.
     *
     * @param serverPlayer The player the spawner is affecting.
     * @return SpawnFactors
     * @see SpawnFactors
     */

    private SpawnFactors initializeSpawnFactor(ServerPlayer serverPlayer) {
        return new SpawnFactors();
    }


}
