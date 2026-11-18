package io.github.emberbocor.pathkeeper.mixin;

import io.github.emberbocor.pathkeeper.PathCover;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
    // Vanilla and NeoForge: level.getBlockState(pos.above()).isAir()
    @Group(name = "pathkeeper$blockAbove", min = 1, max = 1)
    @Redirect(
            method = "useOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z"),
            require = 0
    )
    private boolean pathkeeper$canFlattenUnder(BlockState above, UseOnContext context) {
        return PathCover.allowsFlattening(context.getLevel(), context.getClickedPos().above(), above);
    }

    // Forge: level.isEmptyBlock(pos.above())
    @Group(name = "pathkeeper$blockAbove", min = 1, max = 1)
    @Redirect(
            method = "useOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isEmptyBlock(Lnet/minecraft/core/BlockPos;)Z"),
            require = 0
    )
    private boolean pathkeeper$canFlattenUnderForge(Level level, BlockPos above) {
        return PathCover.allowsFlattening(level, above, level.getBlockState(above));
    }
}
