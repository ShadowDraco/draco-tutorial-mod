package com.shadowdraco.tutorial.lib;


import com.shadowdraco.tutorial.registry.ModEnchantments;
import net.minecraft.block.BlockState;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.ItemStack;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomBlockBreakHandler {

    public CustomBlockBreakHandler() {
        System.out.println("\n--DTM created custom block break handler !---");
    }

    public void handleCustomBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity) {

        // get the item that is equipped in the player's hand
        ItemStack usedStack =  player.getEquippedStack(EquipmentSlot.MAINHAND);
        // if the item has the lovely enchant send a message, spawn a particle, drop the item they broke
        if (EnchantmentHelper.getLevel(ModEnchantments.LOVELY_ENCHANTMENT, usedStack) > 0) {

            if (!world.isClient()) {
                player.sendMessage(Text.literal("Awwwwweee!"));

                world.addParticle(ParticleTypes.HEART, pos.getX(), pos.getY(), pos.getZ(), 0, 2, 0);
                
                // make the player drop the item they just broke if it's a spawner?
                //if (state.getBlock() == Blocks.SPAWNER) {
                    player.dropItem(state.getBlock().asItem().getDefaultStack(), true, true);
                //}
            }
        }
    }
}
