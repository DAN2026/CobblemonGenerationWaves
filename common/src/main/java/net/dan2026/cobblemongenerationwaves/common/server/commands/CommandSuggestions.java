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
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.List;

public class CommandSuggestions {
    
    public static final List<String> VALID_GENS = List.of("gen1", "gen2", "gen3", "gen4", "gen5", "gen6", "gen7", "gen8", "gen9");

    public static final SuggestionProvider<CommandSourceStack> GENERATIONS = (context, builder) ->
            SharedSuggestionProvider.suggest(VALID_GENS, builder);
}