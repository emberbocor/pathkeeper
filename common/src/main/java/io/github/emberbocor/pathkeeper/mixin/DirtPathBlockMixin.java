package io.github.emberbocor.pathkeeper.mixin;

import io.github.emberbocor.pathkeeper.PathCover;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.PathBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PathBlock.class)
public class DirtPathBlockMixin {
    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void pathkeeper$keepUnderNonFullBlock(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos above = pos.above();
        if (!cir.getReturnValue() && PathCover.keepsPath(level, above, level.getBlockState(above))) {
            cir.setReturnValue(true);
        }
    }
}
