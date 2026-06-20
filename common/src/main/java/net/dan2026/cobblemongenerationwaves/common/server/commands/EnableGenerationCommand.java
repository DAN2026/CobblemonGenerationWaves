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
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.dan2026.cobblemongenerationwaves.common.server.spawns.SpawnFactors;
import net.dan2026.cobblemongenerationwaves.common.server.utility.StringUtility;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class EnableGenerationCommand {

    private static final SimpleCommandExceptionType INVALID_GEN =
            new SimpleCommandExceptionType(Component.literal("That is an invalid generation."));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("enablegeneration")
                .requires(s -> s.hasPermission(2))
                .then(Commands.argument("gen", StringArgumentType.word())
                        .suggests(CommandSuggestions.GENERATIONS)
                        .executes(c -> {

                            String gen = StringArgumentType.getString(c, "gen");

                            if (!CommandSuggestions.VALID_GENERATION_SET.contains(gen)) {
                                throw INVALID_GEN.create();
                            }

                            if (SpawnFactors.getAllowedGenerations().contains(gen)) {
                                c.getSource().sendSuccess(() -> StringUtility.createWarning(gen, "is already enabled"), false);
                                return 0;
                            }

                            SpawnFactors.addGeneration(gen);

                            c.getSource().sendSuccess(() -> StringUtility.createStatusMessage(gen, true), true);

                            return 1;

                        })));
    }
}
