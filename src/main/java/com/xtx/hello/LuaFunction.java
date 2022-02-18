package com.xtx.hello;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.Logger;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaFunction {
    public BlockState GetBlock(Level level, int x, int y, int z) {
        return level.getBlockState(new BlockPos(x, y, z));
    }

    public void BlockDig(Level level, int x, int y, int z) {
        level.destroyBlock(new BlockPos(x, y, z), true);

    }
    public static void Lua()
    {
        System.out.println("Hel");
    }
    public static void LuaTest()
    {
        String Path = "C:\\ForgeProject\\1.18.1\\Hello\\src\\main\\java\\com\\xtx\\hello\\res\\lua\\test.lua";	//lua脚本文件所在路径
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(Path).call();
//获取带参函数test
        LuaTable table= LuaTable.tableOf();
        table.set("a",1);
        LuaValue func1 = globals.get(LuaValue.valueOf("test"));
//执行test方法,传入String类型的参数参数
        String data = func1.call(table).toString();
        //打印lua函数回传的数据
        System.out.println(data);
    }

}
