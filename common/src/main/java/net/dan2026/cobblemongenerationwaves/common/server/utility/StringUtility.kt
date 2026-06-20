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

package net.dan2026.cobblemongenerationwaves.common.server.utility

import net.minecraft.network.chat.Component
import java.util.*

class StringUtility {

    private fun formatGen(gen: String): Component {

        val name = gen.substring(0, 3).substring(0, 1).uppercase(Locale.getDefault()) + gen.substring(0, 3).substring(1)

        val number = gen.substring(3)

        return Component.literal("$name $number")
    }


}