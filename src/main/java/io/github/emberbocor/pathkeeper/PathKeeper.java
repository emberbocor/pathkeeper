package io.github.emberbocor.pathkeeper;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(PathKeeper.MODID)
public class PathKeeper {
    public static final String MODID = "pathkeeper";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PathKeeper(IEventBus modEventBus, ModContainer modContainer) {
    }
}
