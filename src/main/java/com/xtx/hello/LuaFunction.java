package com.xtx.hello;

import com.xtx.hello.blocks.PowergenBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.EntityGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.Logger;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import net.minecraftforge.fml.common.Mod;

public class LuaFunction {
    //    private Level level;
//    private Player playerNow;
//    public LuaFunction()
//    {
//        this.playerNow= ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(Player.createPlayerUUID("Dev"));
//        this.level= playerNow.getLevel();
//    }
    public static LuaTable GetBlock(int x, int y, int z) {
        Player playerNow = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(Player.createPlayerUUID("Dev"));
        Level level = playerNow.getLevel();
        BlockState tmp = level.getBlockState(new BlockPos(x, y, z));
        LuaTable table = LuaTable.tableOf();
        table.set("DescriptionId", tmp.getBlock().getDescriptionId());
        return table;
    }

    public void BlockDig(Level level, int x, int y, int z) {
        level.destroyBlock(new BlockPos(x, y, z), true);

    }


}
