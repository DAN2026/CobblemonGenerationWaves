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

package net.dan2026.cobblemongenerationspawns.common.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.dan2026.cobblemongenerationspawns.common.server.spawns.SpawnFactors;
import net.dan2026.cobblemongenerationspawns.common.server.utility.StringUtility;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.Set;

public class ActiveGenerationsCommand {

    /**
     * Registers the active generation command to display currently active generations.
     *
     * @param dispatcher the command dispatcher
     */

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("generation")
                .then(Commands.literal("active")
                        .executes(c -> {
                            Set<String> active = SpawnFactors.getCachedGenerations();

                            if (active.isEmpty()) {
                                c.getSource().sendSuccess(() -> Component.literal("There are no active generations.")
                                        .withStyle(ChatFormatting.YELLOW), false);
                                return 0;
                            }

                            c.getSource().sendSuccess(() -> StringUtility.formatActiveGenerations(active), false);
                            return 1;
                        })));
    }
}
