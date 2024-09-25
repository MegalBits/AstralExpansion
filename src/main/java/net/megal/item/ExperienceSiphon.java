package net.megal.item;

import net.megal.enchantment.ModEnchantments;
import net.megal.entity.DelayedPickupExperienceOrbEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class ExperienceSiphon extends Item {
    public ExperienceSiphon(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("description.experience_siphon.use").formatted(Formatting.GRAY));
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean inCreativeMode = user.getAbilities().creativeMode;
        if (user.totalExperience > 0 || inCreativeMode) {
            ItemStack stack = user.getStackInHand(hand);

            if (!world.isClient()) {
                int xpAmount = 5 + (ModEnchantments.getEnchantmentLevel(stack, (ServerWorld) world, ModEnchantments.EXTRACTION.getRegistryKey()) * 2);
                int cooldown = ModEnchantments.getEnchantmentLevel(stack, (ServerWorld) world, ModEnchantments.QUICKENED.getRegistryKey()) > 0 ? 4 : 8;

                if (!inCreativeMode) {
                    xpAmount = Math.min(xpAmount, user.totalExperience);
                    user.addExperience(-xpAmount);
                }

                DelayedPickupExperienceOrbEntity experienceOrb = new DelayedPickupExperienceOrbEntity(world, user.getX(), user.getY(), user.getZ(), xpAmount);
                world.spawnEntity(experienceOrb);

                stack.damage(1, user, LivingEntity.getSlotForHand(hand));
                user.getItemCooldownManager().set(this, cooldown);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));

            return TypedActionResult.success(stack);
        }

        return super.use(world, user, hand);
    }
}
