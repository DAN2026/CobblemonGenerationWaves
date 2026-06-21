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

package net.dan2026.cobblemongenerationspawns.common.server.data;

import com.cobblemon.mod.common.Cobblemon;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Persists the mod's generation state to the world's disk storage.
 * This class acts as the primary storage for active generations.
 * It is managed by Minecraft's {@link SavedData} system, which handles
 * serialization and persistence across server restarts.
 * This hashset handles the expensive I/O computations.
 */

public class GenerationData extends SavedData {

    /**
     * The internal storage of active generation identifiers.
     * This set is modified only via add/remove methods which trigger disk synchronization.
     */

    private final Set<String> persistentGenerations = new HashSet<>();

    /**
     * Retrieves the set of currently active generation IDs.
     *
     * @return A mutable reference to the internal generation set.
     */

    public Set<String> getPersistentGenerations() {
        return persistentGenerations;
    }

    /**
     * Adds a generation to the persistent storage.
     * Marking the data as "dirty" signals to the engine that the state has
     * changed and must be committed to the .dat file during the next save cycle.
     *
     * @param gen The generation ID to persist (e.g., "gen1").
     */

    public void addPersistentGeneration(String gen) {
        if (persistentGenerations.add(gen)) {
            setDirty();
        }
    }

    /**
     * Removes a generation from the persistent storage.
     * Marking the data as "dirty" ensures the removal is reflected in the
     * saved file on disk.
     *
     * @param gen The generation ID to remove.
     */

    public void removePersistentGeneration(String gen) {
        if (persistentGenerations.remove(gen)) {
            setDirty();
        }
    }

    /**
     * Serializes the current active generations into NBT format for disk storage.
     * This is called automatically by the Minecraft save system.
     *
     * @param tag      The NBT compound tag to inject data into.
     * @param provider The data provider for registry lookups.
     * @return The updated compound tag containing the persisted generation list.
     */

    @Override
    @NotNull
    public CompoundTag save(@NotNull CompoundTag tag, @NotNull HolderLookup.Provider provider) {
        ListTag list = new ListTag();
        for (String gen : persistentGenerations) {
            list.add(StringTag.valueOf(gen));
        }
        tag.put("generations", list);
        return tag;
    }

    /**
     * Deserializes the saved NBT data back into a {@link GenerationData} instance.
     * This reconstructs the state of the mod from the disk when the world loads.
     *
     * @param tag      The NBT tag containing the saved "generations" list.
     * @param provider The data provider for registry lookups.
     * @return A new instance of GenerationData populated with saved values.
     */

    public static GenerationData load(CompoundTag tag, HolderLookup.Provider provider) {
        Cobblemon.LOGGER.info("Attempting to load GenerationData from disk...");

        GenerationData data = new GenerationData();

        if (tag.contains("generations", Tag.TAG_LIST)) {
            ListTag list = tag.getList("generations", Tag.TAG_STRING);
            for (int i = 0; i < list.size(); i++) {
                data.persistentGenerations.add(list.getString(i));
            }
            Cobblemon.LOGGER.info("Successfully loaded {} generations: {}", list.size(), data.persistentGenerations);
        } else {
            Cobblemon.LOGGER.warn("No 'generations' key found in NBT data!");
        }

        return data;
    }

    /**
     * Retrieves or creates the {@link GenerationData} instance for a given world level.
     * Because this uses {@link ServerLevel#getDataStorage()}, the data is
     * strictly bound to the specific world, preventing cross-dimension state leakage.
     *
     * @param level The current {@link ServerLevel} context.
     * @return The unique {@link GenerationData} instance for this dimension.
     */

    public static GenerationData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new Factory<>(
                        GenerationData::new,
                        GenerationData::load,
                        null
                ),
                "cobblemongenerationwaves_data"
        );
    }
}