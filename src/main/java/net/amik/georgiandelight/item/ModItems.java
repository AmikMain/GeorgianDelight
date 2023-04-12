package net.amik.georgiandelight.item;

import net.amik.georgiandelight.GeorgianDelight;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GeorgianDelight.MOD_ID);


    public static final RegistryObject<Item> KHACHAPURI = ITEMS.register("khachapuri",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));
    public static final RegistryObject<Item> RAW_KHACHAPURI = ITEMS.register("raw_khachapuri",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));
    public static final RegistryObject<Item> AJARIAN_KHACHAPURI = ITEMS.register("ajarian_khachapuri",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));
    public static final RegistryObject<Item> RAW_AJARIAN_KHACHAPURI = ITEMS.register("raw_ajarian_khachapuri",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));
    public static final RegistryObject<Item> SULGUNI_CHEESE = ITEMS.register("sulguni_cheese",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));

    public static final RegistryObject<Item> KHACHAPURI_SLICE = ITEMS.register("khachapuri_slice",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));
    public static final RegistryObject<Item> SULGUNI_SLICE = ITEMS.register("sulguni_slice",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));

    public static final RegistryObject<Item> PIECE_OF_DOUGH = ITEMS.register("piece_of_dough",
            () -> new Item(new Item.Properties().tab(ModCreativeModTab.GEORGIAN_DELIGHT_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
