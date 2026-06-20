/*
 *
 * CobblemonGenerationWaves - A NeoForge Minecraft Mod.
 *
 * Copyright (c) 2026 DAN2026. All rights reserved.
 *
 * This software is licensed under the CobblemonGenerationWaves License v1.0.
 * A copy of this License should have been included with this software.
 * If not, you can obtain a copy at [https://github.com/DAN2026/CobblemonGenerationWaves/blob/master/LICENSE].
 */

package net.dan2026.cobblemongenerationwaves.common.server.spawns;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence;
import com.cobblemon.mod.common.api.spawning.position.SpawnablePosition;
import com.cobblemon.mod.common.pokemon.Species;
import net.dan2026.cobblemongenerationwaves.common.server.data.GenerationData;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Handles spawning influence logic for Cobblemon, restricting spawns based on active generation waves.
 * This class caches generation data to avoid repetitive disk access during spawn checks.
 */

public class SpawnFactors implements SpawningInfluence {

    /**
     * A high-speed cache of currently enabled generation strings (e.g., "gen1").
     * This provides O(1) lookups during the intensive spawn checking process.
     */

    private static final Set<String> cachedGenerations = new HashSet<>();

    /*
    AffectWeight needs overriding to increase weights.
    Math involved for proper scaling dependent on total generations active.
    finalWeight = ((totalGenerations - cachedGenerations) * weights)
     */

    /**
     * Evaluates whether a Pokémon is allowed to spawn based on its generation labels.
     *
     * @param detail The spawn detail being processed by the Cobblemon spawning engine.
     * @param spawnablePosition The context of the spawn attempt.
     * @return True if the Pokémon matches an active generation, false otherwise.
     */

    @Override
    public boolean affectSpawnable(@NotNull SpawnDetail detail, @NotNull SpawnablePosition spawnablePosition) {

        if (!(detail instanceof PokemonSpawnDetail pokemonDetail)) {
            return true;
        }

        String speciesName = pokemonDetail.getPokemon().getSpecies();

        if (speciesName == null) return false;

        Species species = getSpeciesByName(speciesName);

        if (species == null) {
            Cobblemon.LOGGER.warn("Could not find species with name: {}", speciesName);
            return false;
        }

        return species
                .getLabels()
                .stream()
                .anyMatch(label -> cachedGenerations
                        .stream()
                        .anyMatch(label::startsWith));
    }

    /**
     * Gets a species object by its name using the Cobblemon registry.
     *
     * @param name The species name (e.g., "aerodactyl").
     * @return The {@link Species} object, or null if no match exists.
     */
    @Nullable
    private Species getSpeciesByName(@NotNull String name) {
        return PokemonSpecies.getByName(name);
    }

    /**
     * Adds a generation to persistent storage and updates the runtime cache.
     *
     * @param level The server level.
     * @param gen   The generation string to add.
     */

    public static void addGeneration(@NotNull ServerLevel level, @NotNull String gen) {
        GenerationData data = GenerationData.get(level);
        data.addPersistentGeneration(gen);
        updateCachedGenerations(level);
    }

    /**
     * Removes a generation from persistent storage and updates the runtime cache.
     *
     * @param level The server level.
     * @param gen   The generation string to remove.
     */

    public static void removeGeneration(@NotNull ServerLevel level, @NotNull String gen) {
        GenerationData.get(level).removePersistentGeneration(gen);
        updateCachedGenerations(level);
    }

    /**
     * Gets all allowed generations saved in the persistent world data.
     *
     * @param level The server level.
     * @return An unmodifiable set of allowed generations.
     */

    public static Set<String> getPersistentGenerations(@NotNull ServerLevel level) {
        return Collections.unmodifiableSet(GenerationData.get(level).getPersistentGenerations());
    }


    /**
     * Updates the runtime cache using the data saved in the persistent world storage.
     *
     * @param serverLevel the server level containing the persistent data
     */

    public static void updateCachedGenerations(ServerLevel serverLevel) {

        Set<String> persistentGens = GenerationData.get(serverLevel).getPersistentGenerations();

        cachedGenerations.clear();
        cachedGenerations.addAll(persistentGens);

        Cobblemon.LOGGER.info("Generation Cache has been updated. Generations: {}", persistentGens);

    }

    /**
     * Gets the current runtime spawn cache.
     * @return An unmodifiable view of the currently cached generation strings.
     */

    public static Set<String> getCachedGenerations() {
        return Collections.unmodifiableSet(cachedGenerations);
    }


}