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
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class HelpCommand {

    /**
     * Registers the help command and outputs custom formatted documentation.
     *
     * @param dispatcher The command dispatcher.
     */

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("generation")
                .then(Commands.literal("help")
                .requires(s -> s.hasPermission(2))
                        .executes(c -> {

                            c.getSource().sendSuccess(() -> Component.literal("\nGeneration Spawns Commands:\n")
                                    .withStyle(ChatFormatting.WHITE, ChatFormatting.BOLD), false);

                            c.getSource().sendSuccess(() -> formatHelp("/generation enable ", "<generation>", "Enabled spawning for that generation.\n", ChatFormatting.GREEN), false);

                            c.getSource().sendSuccess(() -> formatHelp("/generation disable ", "<generation>", "Disable spawning for that generation.\n", ChatFormatting.RED), false);

                            c.getSource().sendSuccess(() -> formatHelp("/generation active", "", "Shows what generations are active.\n", ChatFormatting.YELLOW), false);

                            return 1;
                        })));
    }

    /**
     * Helper to format help lines.
     * This may get moved to string utility.
     *
     * @param cmd   The command string.
     * @param param The parameter string.
     * @param desc  The description of the command.
     * @param color The ChatFormatting color for the command and parameter.
     * @return A formatted {@link MutableComponent} for the help menu.
     */

    private static MutableComponent formatHelp(String cmd, String param, String desc, ChatFormatting color) {
        return Component.literal(cmd).withStyle(color)
                .append(Component.literal(param).withStyle(color, ChatFormatting.ITALIC))
                .append(Component.literal(" -> ").withStyle(ChatFormatting.WHITE))
                .append(Component.literal(desc).withStyle(ChatFormatting.WHITE));
    }
}
