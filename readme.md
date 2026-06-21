# Cobblemon: Generation Spawns
![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.1-blue)
![Loader](https://img.shields.io/badge/Loader-NeoForge/Fabric-orange)
![License](https://img.shields.io/badge/LICENSE-CobblemonGenerationSpawns%20v1.0-green)

Cobblemon: Generation Spawns is a Minecraft mod designed to manage and restrict Pokémon spawning behavior in your game world based on generation "waves." This mod is built to be cross-platform, supporting both **Fabric** and **NeoForge**.

## Features
- **Dynamic Spawn Filtering:** Control which Pokémon generations (e.g., `gen1`, `gen8`) are allowed to spawn in your world.
- **Support for Sub-Generations:** Flexible matching allows for variant support (e.g., enabling `gen8` automatically allows `gen8a`, `gen8b`, etc.).
- **Live Configuration:** Use in-game commands to toggle allowed generations without requiring a server restart.
- **Detailed Logging:** Real-time console tracking of successful spawns.

## Commands & Usage

| Command               | Arguments | Permission | Description | Example Usage |
|:----------------------| :--- | :--- | :--- | :--- |
| `/generation enable`  | `<generation>` | OP Level 2 | Permanently enables spawning for the specified generation string. | `/enablegeneration gen1` |
| `/generation disable` | `<generation>` | OP Level 2 | Disables spawning for the specified generation string. | `/disablegeneration gen1` |
| `/generation active`  | *None* | **All Players** | Lists all currently active Pokémon generations allowed to spawn. | `/activegenerations` |
| `/generation debug`   | *None* | OP Level 2 | Displays current in-memory cache states and persistent disk storage contents. | `/generationwave debug` |
| `/generation help`    | *None* | OP Level 2 | Prints a quick-reference summary of syntax and instructions for administering generation waves. | `/generationwave help` |

## Live Configuration & Spawn Calculations

The mod uses an optimised runtime memory cache to intercept and validate spawn requests instantly, completely eliminating disk I/O performance overhead during wild spawns.

When an administrator alters the spawning rules:
1. The choice is instantly written to the Overworld save's persistent data file.
2. The internal runtime cache synchronizes automatically across the server without requiring a world reload or server restart.
3. This background update immediately recalculates pool weights and constraints for Cobblemon's `/checkspawns` command. Players running `/checkspawns` will instantly see the updated pools, odds, and legal species reflecting the active generation waves.

## Important: Default Spawning Behavior

> **By default, CobblemonGenerationWaves has NO generations enabled.**

When you launch a new world for the first time, all natural Pokémon spawning will be blocked until an administrator explicitly registers a wave. For instance, to start your server with only the original Kanto Pokémon, run:
```text
/enablegeneration gen1