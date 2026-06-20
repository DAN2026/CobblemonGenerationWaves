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

package net.dan2026.cobblemongenerationwaves.common.server.spawns;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence;
import com.cobblemon.mod.common.api.spawning.position.SpawnablePosition;
import com.cobblemon.mod.common.pokemon.Species;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SpawnFactors implements SpawningInfluence {


    /**
     * Generations that are allowed to spawn.
     */

    private static final Set<String> ALLOWED_GENERATIONS = new HashSet<>(Set.of("gen1", "gen8", "gen3"));

    @Override
    public boolean affectSpawnable(@NotNull SpawnDetail detail, @NotNull SpawnablePosition spawnablePosition) {

        // Check if the entity trying to spawn is a pokemon and not an NPC.

        if (!(detail instanceof PokemonSpawnDetail pokemonDetail)) {
            return true;
        }

        String speciesName = pokemonDetail.getPokemon().getSpecies();

        // Returns early if the pokemon datapack is incorrectly formatted.

        if (speciesName == null) {
            return false;
        }

        Species species = getSpeciesByName(speciesName);

        if (species == null) {
            Cobblemon.LOGGER.log(Level.WARN, "Could not find species with name: {}", speciesName);
            return false;
        }

        boolean spawnable = species
                .getLabels()
                .stream()
                .anyMatch(
                        label -> ALLOWED_GENERATIONS
                                .stream()
                                .anyMatch(
                                        label::startsWith
                                )
                );

        if (spawnable) {
            logSpawnDebug(species);
        }

        return spawnable;
    }

    /**
     * Resolves a species from a name string using the Cobblemon registry.
     *
     * @param name The species name (e.g., "aerodactyl").
     * @return The {@link Species} object, or null if no match exists.
     */

    @Nullable
    private Species getSpeciesByName(@NotNull String name) {
        try {
            return PokemonSpecies.getByName(name);
        } catch (Exception e) {
            Cobblemon.LOGGER.log(Level.ERROR, "Error retrieving species by name: {}", name, e);
            return null;
        }
    }

    /**
     * Logs the spawn debug information in the required format.
     *
     * @param species The species to log.
     * @see Species#getLabels()
     */

    private void logSpawnDebug(@NotNull Species species) {
        String generation = "Unknown";

        for (String label : species.getLabels()) {
            if (label.startsWith("gen")) {
                generation = label;
                break;
            }
        }

        Cobblemon.LOGGER.log(Level.INFO,
                "Species: {}, Generation: {}",
                species.getName(),
                generation
        );
    }

    /**
     * Updates the allowed generations list dynamically.
     *
     * @param newGenerations A set of generation strings like "gen1", "gen2".
     */

    public static void setAllowedGenerations(@NotNull Set<String> newGenerations) {
        ALLOWED_GENERATIONS.clear();
        ALLOWED_GENERATIONS.addAll(newGenerations);
    }

    public static void addGeneration(String gen) { ALLOWED_GENERATIONS.add(gen); }

    public static void removeGeneration(String gen) { ALLOWED_GENERATIONS.remove(gen); }

    public static Set<String> getAllowedGenerations() { return Collections.unmodifiableSet(ALLOWED_GENERATIONS); }


}