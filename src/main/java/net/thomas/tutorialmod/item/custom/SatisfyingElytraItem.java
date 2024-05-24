package net.thomas.tutorialmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class SatisfyingElytraItem extends ElytraItem {
    public SatisfyingElytraItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        entity.sendMessage(Text.literal(String.valueOf(slot)));
    }
}
