/**
 *
 * CobblemonGenerationWaves - A NeoForge Minecraft Mod.
 * <p>
 * Copyright (c) 2026 DAN2026. All rights reserved.
 * <p>
 * This software is licensed under the CobblemonGenerationWaves Licence v1.0.
 * A copy of this Licence should have been included with this software.
 * If not, you can obtain a copy at [https://github.com/DAN2026/CobblemonGenerationWaves/blob/master/LICENSE].
 */
package net.dan2026.cobblemongenerationwaves.common.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.dan2026.cobblemongenerationwaves.common.server.spawns.SpawnFactors;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

import java.util.Set;

/**
 * Command utility to debug generation data by outputting both cached and persistent states.
 */

public final class DebugCommand {

    /**
     * Constructs a new command instance.
     */

    private DebugCommand() {
    }

    /**
     * Registers the debug subcommand under the generationwave base command.
     *
     * @param dispatcher the command dispatcher
     */

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("generationwave")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("debug")
                        .executes(DebugCommand::execute)));
    }

    /**
     * Executes the debug command, retrieving data from the overworld and cache.
     *
     * @param context the command context
     * @return 1 after execution
     */

    private static int execute(CommandContext<CommandSourceStack> context) {
        ServerLevel overworld = context.getSource().getServer().overworld();

        Set<String> cachedGens = SpawnFactors.getCachedGenerations();
        Set<String> persistentGens = SpawnFactors.getPersistentGenerations(overworld);

        String cachedJoined = String.join(", ", cachedGens);
        String persistentJoined = String.join(", ", persistentGens);

        context.getSource().sendSuccess(() -> Component.literal("Cached Generations: [")
                .withStyle(ChatFormatting.WHITE)
                .append(Component.literal(cachedJoined).withStyle(ChatFormatting.GREEN))
                .append(Component.literal("]").withStyle(ChatFormatting.WHITE)), false);

        context.getSource().sendSuccess(() -> Component.literal("Persistent Generations: [")
                .withStyle(ChatFormatting.WHITE)
                .append(Component.literal(persistentJoined).withStyle(ChatFormatting.GREEN))
                .append(Component.literal("]").withStyle(ChatFormatting.WHITE)), false);

        return 1;
    }
}