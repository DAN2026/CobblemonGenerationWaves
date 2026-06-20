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

package net.dan2026.cobblemongenerationwaves.common.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.dan2026.cobblemongenerationwaves.common.server.spawns.SpawnFactors;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ActiveGenerationsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("activeGeneration")
                .executes(c -> {
                    String list = String.join(", ", SpawnFactors.getAllowedGenerations());
                    c.getSource().sendSuccess(() -> Component.literal("Active generations: " + list), false);
                    return 1;
                }));
    }

}
