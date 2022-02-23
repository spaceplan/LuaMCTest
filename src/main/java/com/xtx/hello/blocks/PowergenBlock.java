package com.xtx.hello.blocks;

import com.xtx.hello.LuaFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import javax.annotation.Nullable;
import java.util.List;

public class PowergenBlock extends Block implements EntityBlock {

    public static final String MESSAGE_POWERGEN = "message.powergen";
    public static final String SCREEN_TUTORIAL_POWERGEN = "screen.tutorial.powergen";
    public static Level nowLevel;
    private static final VoxelShape RENDER_SHAPE = Shapes.box(0.1, 0.1, 0.1, 0.9, 0.9, 0.9);

    public PowergenBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(2.0f)
                .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
                .requiresCorrectToolForDrops()
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader, BlockPos pos) {
        return RENDER_SHAPE;
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent(MESSAGE_POWERGEN, Integer.toString(PowergenBE.POWERGEN_GENERATE))
                .withStyle(ChatFormatting.BLUE));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PowergenBE(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof PowergenBE tile) {
                tile.tickServer();
            }
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.POWERED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return super.getStateForPlacement(context).setValue(BlockStateProperties.POWERED, false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            System.out.println("Hello From code1!");
            nowLevel=level;
            this.LuaTest(player.createPlayerUUID("Dev").toString(),pos.getX(), pos.getY(), pos.getZ());
        }
//        if (!level.isClientSide) {
//            BlockEntity be = level.getBlockEntity(pos);
//            if (be instanceof PowergenBE) {
//                MenuProvider containerProvider = new MenuProvider() {
//                    @Override
//                    public Component getDisplayName() {
//                        return new TranslatableComponent(SCREEN_TUTORIAL_POWERGEN);
//                    }
//
//                    @Override
//                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
//                        return new PowergenContainer(windowId, pos, playerInventory, playerEntity);
//                    }
//                };
//                NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
//            } else {
//                throw new IllegalStateException("Our named container provider is missing!");
//            }
//        }
        return InteractionResult.SUCCESS;
    }
    public void LuaTest(String UUID,int x,int y,int z)
    {
        String Path = "C:\\ForgeProject\\1.18.1\\Hello\\src\\main\\java\\com\\xtx\\hello\\res\\lua\\test.lua";	//lua脚本文件所在路径
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(Path).call();
//获取带参函数test
        LuaTable table= LuaTable.tableOf();
        table.set("x",x);
        table.set("y",y);
        table.set("z",z);
        table.set("uuid",UUID);
        LuaValue func1 = globals.get(LuaValue.valueOf("main"));
//执行test方法,传入String类型的参数参数
        String data = func1.call(table).toString();
        //打印lua函数回传的数据
        System.out.println(data);
    }
}