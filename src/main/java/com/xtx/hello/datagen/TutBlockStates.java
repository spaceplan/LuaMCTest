package com.xtx.hello.datagen;

import com.xtx.hello.Hello;
import com.xtx.hello.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {

    public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, Hello.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.MYSTERIOUS_ORE_OVERWORLD.get());
        simpleBlock(Registration.MYSTERIOUS_ORE_NETHER.get());
        simpleBlock(Registration.MYSTERIOUS_ORE_END.get());
        simpleBlock(Registration.MYSTERIOUS_ORE_DEEPSLATE.get());
    }
}