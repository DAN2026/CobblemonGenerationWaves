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

package net.dan2026.cobblemongenerationspawns.fabric;

import net.dan2026.cobblemongenerationspawns.common.server.registry.CommandRegistry;
import net.dan2026.cobblemongenerationspawns.common.server.registry.ServerRegistry;
import net.dan2026.cobblemongenerationspawns.common.server.registry.SpawningRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class FabricServer implements ModInitializer {

    @Override
    public void onInitialize() {

        ServerLifecycleEvents.SERVER_STARTED.register(SpawningRegistry::register);

        CommandRegistrationCallback.EVENT.register(
                (dispatcher,
                 registryAccess,
                 environment) -> CommandRegistry.register(dispatcher));

        ServerLifecycleEvents.SERVER_STARTED.register(ServerRegistry::register);
    }


}
