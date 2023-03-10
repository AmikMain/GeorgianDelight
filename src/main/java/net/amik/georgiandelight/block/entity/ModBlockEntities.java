package net.amik.georgiandelight.block.entity;

import net.amik.georgiandelight.GeorgianDelight;
import net.amik.georgiandelight.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, GeorgianDelight.MOD_ID);

    public static final RegistryObject<BlockEntityType<CheeseBasinBlockEntity>> CHEESE_BASIN =
            BLOCK_ENTITIES.register("cheese_basin", () -> BlockEntityType.Builder.of
                    (CheeseBasinBlockEntity::new, ModBlocks.CHEESE_BASIN.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
