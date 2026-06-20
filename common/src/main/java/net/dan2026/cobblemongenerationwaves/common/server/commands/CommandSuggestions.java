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

import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Set;

public class CommandSuggestions {

    /**
     * The list of all generation IDs supported by the mod.
     */

    public static final List<String> SUPPORTED_GENERATION_IDS = List.of(
            "gen1", "gen2", "gen3", "gen4", "gen5", "gen6", "gen7", "gen8", "gen9"
    );

    /**
     * A HashSet for O(1) lookup performance when validating user input in commands.
     */

    public static final Set<String> VALID_GENERATION_SET = Set.copyOf(SUPPORTED_GENERATION_IDS);

    public static final SuggestionProvider<CommandSourceStack> GENERATIONS = (context, builder) -> {
        for (String id : SUPPORTED_GENERATION_IDS) {

            String prefix = id.replaceAll("\\d", "");
            String number = id.replaceAll("\\D", "");
            String display = prefix.substring(0, 1).toUpperCase() + prefix.substring(1) + "_" + number;

            builder.suggest(id, Component.literal(display));
        }
        return builder.buildFuture();
    };
}