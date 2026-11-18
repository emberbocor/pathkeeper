package io.github.emberbocor.pathkeeper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.jetbrains.annotations.Nullable;

/**
 * Marks the evaluation of the minecraft:shovel block transformer, so that only its "air above" check is replaced.
 */
public final class ShovelScope {
    private static final ThreadLocal<Boolean> SHOVEL = ThreadLocal.withInitial(() -> false);

    private ShovelScope() {
    }

    @Nullable
    public static BlockState evaluate(BlockTransformer transformer, BlockStateProvider provider, LevelAccessor level, RandomSource random, BlockPos pos) {
        boolean previous = SHOVEL.get();
        SHOVEL.set(isShovel(transformer, level));
        try {
            return provider.getOptionalState(level, random, pos);
        } finally {
            SHOVEL.set(previous);
        }
    }

    public static boolean replacesCheck(TagKey<Block> tag, Vec3i offset) {
        return SHOVEL.get() && BlockTags.AIR.equals(tag) && offset.getX() == 0 && offset.getY() == 1 && offset.getZ() == 0;
    }

    private static boolean isShovel(BlockTransformer transformer, LevelAccessor level) {
        return level.registryAccess()
                .lookupOrThrow(Registries.BLOCK_TRANSFORMER)
                .getResourceKey(transformer)
                .filter(BlockTransformers.SHOVEL::equals)
                .isPresent();
    }
}
