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

package net.dan2026.cobblemongenerationwaves.forge;

import net.dan2026.cobblemongenerationwaves.common.CommonServer;
import net.dan2026.cobblemongenerationwaves.common.server.commands.ToggleGenerationsCommand;
import net.dan2026.cobblemongenerationwaves.common.server.registry.CommandRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(CommonServer.MOD_ID)
public class ForgeServer {

    public ForgeServer() {

    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandRegistry.register(event.getDispatcher());
    }

}
