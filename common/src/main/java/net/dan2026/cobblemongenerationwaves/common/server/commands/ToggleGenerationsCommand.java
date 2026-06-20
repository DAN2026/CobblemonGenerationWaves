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
import net.dan2026.cobblemongenerationwaves.common.server.spawns.SpawnFactors;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.HashSet;
import java.util.Set;

@Deprecated
public class ToggleGenerationsCommand {

    private static final Set<String> currentGenerations = new HashSet<>(Set.of("gen1", "gen8"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("toggleGen")
                .requires(source -> source.hasPermission(2)) // OP level 2 required
                .then(Commands.argument("generation", StringArgumentType.string())
                        .executes(context -> {
                            String gen = StringArgumentType.getString(context, "generation");
                            if (currentGenerations.contains(gen)) {
                                currentGenerations.remove(gen);
                                context.getSource().sendSuccess(() -> Component.literal("Disabled " + gen), true);
                            } else {
                                currentGenerations.add(gen);
                                context.getSource().sendSuccess(() -> Component.literal("Enabled " + gen), true);
                            }

                            SpawnFactors.setAllowedGenerations(currentGenerations);
                            return 1;
                        })));
    }

}
