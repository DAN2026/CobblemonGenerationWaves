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

package net.dan2026.cobblemongenerationspawns.forge;

import net.dan2026.cobblemongenerationspawns.common.CommonServer;
import net.dan2026.cobblemongenerationspawns.common.server.registry.CommandRegistry;
import net.dan2026.cobblemongenerationspawns.common.server.registry.ServerRegistry;
import net.dan2026.cobblemongenerationspawns.common.server.registry.SpawningRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@Mod(CommonServer.MOD_ID)
public class ForgeServer {

    public ForgeServer() {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandRegistry.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        SpawningRegistry.register(event.getServer());
        ServerRegistry.register(event.getServer());
    }

}
