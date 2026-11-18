package io.github.emberbocor.pathkeeper.mixin;

import io.github.emberbocor.pathkeeper.ShovelScope;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockTransformer.class)
public class BlockTransformerMixin {
    @Redirect(
            method = "transformBlock",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/stateproviders/BlockStateProvider;getOptionalState(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;")
    )
    @Nullable
    private BlockState pathkeeper$evaluate(BlockStateProvider provider, LevelAccessor level, RandomSource random, BlockPos pos) {
        return ShovelScope.evaluate((BlockTransformer) (Object) this, provider, level, random, pos);
    }
}
