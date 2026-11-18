# Path Keeper

Keep your dirt paths under slabs, stairs, fences and other blocks that are not full cubes, and make new paths under them with a shovel.

In vanilla Minecraft a dirt path turns into dirt as soon as a slab, stairs, a trapdoor, a fence, a wall, a sign and many other blocks are placed on top of it ([MC-84731](https://bugs.mojang.com/browse/MC-84731)), and a shovel only makes a path when the block above is air ([MC-85237](https://bugs.mojang.com/browse/MC-85237)). With this mod, paths stay under every block whose outline is not a full cube, and shovels make paths under those blocks too.

The mod runs on the server only. Players join with a plain vanilla client and still see everything.

Available for NeoForge, Forge and Fabric.

## Features

- **Paths stay under non-full blocks.** Slabs, stairs, trapdoors, fences, walls, signs, chests, campfires and every other block that is not a full cube no longer turn the path below into dirt.
- **Shovels make paths under them.** Grass, dirt, podzol, coarse dirt, mycelium and rooted dirt can be flattened into a path while such a block sits on top.
- **Fluids still stop the shovel.** Water, lava and waterlogged blocks above keep the shovel from making a path, as in vanilla.
- **Full blocks stay vanilla.** Stone, planks, glass, leaves and other full blocks still turn the path into dirt.

There is no config file.

## How it works

The rule looks at the outline of the block above the path: the box you see when you aim at the block. If that outline is not a full cube, the path stays.

Paths stay under, for example:

- slabs (top or bottom), stairs, trapdoors (top or bottom)
- fences, fence gates, walls, glass panes, iron bars, chains
- signs, banners, heads
- chests, beds, anvils, lecterns, cauldrons, hoppers, composters, campfires, flower pots
- scaffolding

Paths still turn into dirt under blocks with a full-cube outline, for example stone, dirt, planks, glass, leaves, ice, soul sand, mud, honey blocks, barrels and double slabs.

Shovels follow the same rule. A shovel turns the block into a path when the block above is air, or when its outline is not a full cube, it holds no fluid and it can stand on a path. A waterlogged slab or a block with water or lava in it still stops the shovel, and so does every block with a full-cube outline.

What stays vanilla:

- **Look and height.** Paths look the same and stay 15/16 of a block high.
- **Placing blocks on paths.** Standing torches, doors, rails, pressure plates, lanterns, candles, flowers and saplings still cannot be placed on a path. A shovel does not make a path under them either, so nothing breaks or drops.
- **Farmland and hoes.** Hoes, farmland and trampling work as in vanilla.
- **Other shovel uses.** Putting out campfires with a shovel works as in vanilla.
- **Mobs.** Pathfinding and mob behaviour on paths are not changed.

## Requirements

| | NeoForge | Forge | Fabric |
|---|---|---|---|
| Minecraft | 1.21.1 | 1.21.1 | 1.21 or 1.21.1 |
| Loader | NeoForge 21.1.1 or newer | Forge 52.0.0 or newer | Fabric Loader 0.15.10 or newer |
| Jar | `pathkeeper-neoforge-1.21.1-<version>.jar` | `pathkeeper-forge-1.21.1-<version>.jar` | `pathkeeper-fabric-1.21-1.21.1-<version>.jar` |

On Minecraft 1.21.1, NeoForge and Forge are separate loaders that do not run each other's mods, so each has its own jar. Pick the jar that matches your server's loader.

The Fabric version does not need Fabric API.

### Client and server

Install the mod on the server only, on every loader. It adds no items, blocks, entities, sounds, network messages or registries, so the server has nothing a client needs to know about:

- **NeoForge:** vanilla clients and NeoForge clients without the mod can join.
- **Forge:** vanilla clients and Forge clients without the mod can join. The mod is marked as server-only, so a Forge client without it still shows the server as compatible in the server list.
- **Fabric:** vanilla clients and Fabric clients without the mod can join.

Players without the mod see the paths like everyone else. When such a player makes a path under a non-full block, the path appears and the shovel loses durability, but that player does not see their own arm swing or hear the shovel sound; other players nearby hear it. Installing the mod on a client as well brings back the swing and the sound for that player.

In singleplayer, install it in your own game; the built-in server runs it.

## Removing the mod

The world opens normally. The mod saves nothing of its own. Paths under non-full blocks stay until the block on top of them changes, for example when a fence above connects to a new neighbour or a slab is replaced; then the vanilla rule applies again and the path turns into dirt.

## Compatibility

- Blocks from other mods follow the same outline rule.
- On NeoForge and Forge, blocks that other mods make flattenable with a shovel follow the new rule for the block above as well.
- Shovels from other mods are covered when they use the vanilla shovel code. Tools with their own path-making code keep their own rules.

## Building from source

```
./gradlew build
```

The jars are written to `neoforge/build/libs/`, `forge/build/libs/` and `fabric/build/libs/`.

## License

MIT.
