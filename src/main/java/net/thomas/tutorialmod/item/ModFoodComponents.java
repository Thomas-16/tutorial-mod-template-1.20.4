package net.thomas.tutorialmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent TOMATO = new FoodComponent.Builder().hunger(3).saturationModifier(.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1), .5f).alwaysEdible().build();

}
