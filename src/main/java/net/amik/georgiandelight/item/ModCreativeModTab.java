package net.amik.georgiandelight.item;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTab {
    public static final CreativeModeTab GEORGIAN_DELIGHT_TAB = new CreativeModeTab("georgiandelighttab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.KHACHAPURI.get());
        }

    };
}
