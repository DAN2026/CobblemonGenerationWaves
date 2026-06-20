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
import net.dan2026.cobblemongenerationwaves.common.server.utility.StringUtility;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Set;

public class ActiveGenerationsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("activegeneration")
                .executes(c -> {

                    Set<String> active = SpawnFactors.getAllowedGenerations();

                    if (active.isEmpty()) {
                        c.getSource().sendSuccess(() -> Component.literal("There are no active generations.")
                                .withStyle(ChatFormatting.YELLOW), false);
                        return 0;
                    }

                    MutableComponent message =
                            Component
                            .literal("Active Generations: ")
                            .withStyle(ChatFormatting.WHITE);

                    message.append(StringUtility.formatList(active, ChatFormatting.GREEN));

                    c.getSource().sendSuccess(() -> StringUtility.formatActiveGenerations(active), false);

                    return 1;

                }));
    }
}
