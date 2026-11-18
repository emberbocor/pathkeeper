package io.github.emberbocor.pathkeeper.mixin;

import io.github.emberbocor.pathkeeper.PathCover;
import io.github.emberbocor.pathkeeper.ShovelScope;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.blockpredicates.MatchingBlockTagPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.StateTestingPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StateTestingPredicate.class)
public abstract class StateTestingPredicateMixin {
    @Shadow
    @Final
    protected Vec3i offset;

    @Inject(method = "test(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private void pathkeeper$shovelAboveCheck(LevelAccessor level, BlockPos origin, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof MatchingBlockTagPredicate predicate
                && ShovelScope.replacesCheck(((MatchingBlockTagPredicateAccessor) predicate).pathkeeper$getTag(), this.offset)) {
            BlockPos above = origin.offset(this.offset);
            cir.setReturnValue(PathCover.allowsFlattening(level, above, level.getBlockState(above)));
        }
    }
}
