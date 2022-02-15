package com.xtx.hello;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaFunction {
    public BlockState GetBlock(Level level, int x, int y, int z) {
        return level.getBlockState(new BlockPos(x, y, z));
    }

    public void BlockDig(Level level, int x, int y, int z) {
        level.destroyBlock(new BlockPos(x, y, z), true);
        String luaStr = "print 'hello,world!'";
        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.load(luaStr);
        chunk.call();
    }
}
