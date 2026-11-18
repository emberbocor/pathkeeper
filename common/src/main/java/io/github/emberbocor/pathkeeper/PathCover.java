package io.github.emberbocor.pathkeeper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class PathCover {
    private PathCover() {
    }

    public static boolean keepsPath(BlockGetter level, BlockPos pos, BlockState state) {
        return !Block.isShapeFullBlock(state.getShape(level, pos));
    }

    public static boolean allowsFlattening(BlockGetter level, BlockPos pos, BlockState state) {
        return state.isAir() || state.getFluidState().isEmpty() && keepsPath(level, pos, state);
    }
}
