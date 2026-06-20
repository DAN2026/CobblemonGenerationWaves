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

package net.dan2026.cobblemongenerationwaves.common.server.registry;

import com.mojang.brigadier.CommandDispatcher;
import net.dan2026.cobblemongenerationwaves.common.server.commands.ActiveGenerationsCommand;
import net.dan2026.cobblemongenerationwaves.common.server.commands.DisableGenerationCommand;
import net.dan2026.cobblemongenerationwaves.common.server.commands.EnableGenerationCommand;
import net.dan2026.cobblemongenerationwaves.common.server.commands.HelpCommand;
import net.minecraft.commands.CommandSourceStack;

public class CommandRegistry {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        EnableGenerationCommand.register(dispatcher);
        DisableGenerationCommand.register(dispatcher);
        ActiveGenerationsCommand.register(dispatcher);
        HelpCommand.register(dispatcher);
    }

}
