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


package net.dan2026.cobblemongenerationwaves.common.server.utility;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Utility class for formatting generation strings and lists into Minecraft {@link Component} objects.
 */

public class StringUtility {

    /**
     * Formats a raw generation string (e.g., "gen1") into a displayable format (e.g., "Gen 1").
     *
     * @param gen The raw generation string to format.
     * @return A {@link MutableComponent} containing the formatted string.
     * @see #formatList(Set, ChatFormatting)
     */

    public static MutableComponent formatGen(String gen) {
        if (gen == null || gen.isEmpty()) return Component.literal("");

        String[] parts = gen.split("(?<=\\D)(?=\\d)");

        if (parts.length < 2) return Component.literal(gen);

        String prefix = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
        return Component.literal(prefix + " " + parts[1]);
    }

    /**
     * Formats a set of generation strings into a comma-separated list of components.
     *
     * @param gens The set of raw generation strings.
     * @param color The {@link ChatFormatting} color to apply to each generation name.
     * @return A single {@link MutableComponent} containing the joined list.
     * @see #formatGen(String)
     */

    public static MutableComponent formatList(Set<String> gens, ChatFormatting color) {

        MutableComponent result = Component.literal("");
        Iterator<String> it = gens.iterator();

        while (it.hasNext()) {
            result.append(formatGen(it.next()).withStyle(color));
            if (it.hasNext()) {
                result.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
            }
        }
        return result;
    }

    /**
     * Creates a standard feedback component with a prefix and a formatted generation suffix.
     *
     * @param prefix The text prefix (e.g., "Enabled Generation: ").
     * @param gen The raw generation string.
     * @param color The {@link ChatFormatting} to apply to the generation name.
     * @return A {@link MutableComponent} combining the prefix and styled generation.
     */

    @Deprecated
    public static MutableComponent createFeedback(String prefix, String gen, ChatFormatting color) {
        return Component.literal(prefix).append(formatGen(gen).withStyle(color));
    }

    /**
     * Creates a warning message with the generation and state, without a prefix.
     *
     * @param gen   The internal ID (e.g., "gen1").
     * @param state The warning state message.
     * @return A {@link MutableComponent} formatted as "[Generation] [state]."
     */

    public static MutableComponent createWarning(String gen, String state) {
        return formatGen(gen).withStyle(ChatFormatting.YELLOW)
                .append(Component.literal(" " + state + ".").withStyle(ChatFormatting.YELLOW));
    }

    /**
     * Creates a status message for generations, where the entire message is colored.
     *
     * @param gen The internal ID (e.g., "gen1").
     * @param enabled True if enabled (Green), false if disabled (Red).
     * @return A {@link MutableComponent} formatted as "Generation [number] has been [status]."
     */

    public static MutableComponent createStatusMessage(String gen, boolean enabled) {
        String[] parts = gen.split("(?<=\\D)(?=\\d)");
        String number = (parts.length > 1) ? parts[1] : gen;

        ChatFormatting color = enabled ? ChatFormatting.GREEN : ChatFormatting.RED;
        String status = enabled ? "enabled" : "disabled";

        return Component.literal("Generation " + number + " has been " + status + ".")
                .withStyle(color);
    }

    /**
     * Formats the active generations into a sorted, comma-separated list with an "and" for the last item.
     *
     * @param activeGens A set of currently active generation IDs.
     * @return A {@link MutableComponent} stating which generations are active.
     */

    public static MutableComponent formatActiveGenerations(Set<String> activeGens) {
        if (activeGens.isEmpty()) {
            return Component.literal("There are no active generations.").withStyle(ChatFormatting.YELLOW);
        }

        List<String> sortedNumbers = activeGens.stream()
                .map(gen -> gen.replaceAll("\\D", ""))
                .map(Integer::parseInt)
                .sorted()
                .map(String::valueOf)
                .toList();

        String joined;
        int size = sortedNumbers.size();

        if (size == 1) {
            joined = sortedNumbers.getFirst();
        } else {
            String allButLast = String.join(", ", sortedNumbers.subList(0, size - 1));
            joined = allButLast + " and " + sortedNumbers.get(size - 1);
        }

        String label = (size == 1) ? "Generation " : "Generations ";
        String suffix = (size == 1) ? " is active." : " are active.";

        return Component.literal(label + joined + suffix)
                .withStyle(ChatFormatting.YELLOW);
    }

}