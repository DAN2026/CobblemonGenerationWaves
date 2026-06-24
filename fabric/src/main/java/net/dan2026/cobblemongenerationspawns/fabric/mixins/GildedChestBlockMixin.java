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

package net.dan2026.cobblemongenerationspawns.fabric.mixins;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.block.chest.GildedChestBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GildedChestBlock.class)
public class GildedChestBlockMixin {


    /**
     * @author DAN2026
     * @reason Force isFake to return false to prevent Gimmighoul spawning.
     */
    @Overwrite(remap = false)
    public final boolean isFake() {

        Cobblemon.LOGGER.info("Spawn Gimmighoul: ", false);

        return false;
    }

}
