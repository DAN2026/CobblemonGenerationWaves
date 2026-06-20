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

package net.dan2026.cobblemongenerationwaves.common.server.events;

import net.dan2026.cobblemongenerationwaves.common.server.spawns.SpawnFactors;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;


public class ServerEvents {

    public static void onServerStarted(MinecraftServer server) {

        ServerLevel overworld = server.overworld();

        SpawnFactors.updateCachedGenerations(overworld);

    }

}
