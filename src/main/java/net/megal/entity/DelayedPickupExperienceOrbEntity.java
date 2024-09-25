package net.megal.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class DelayedPickupExperienceOrbEntity extends ExperienceOrbEntity {
    private int ticksUntilPickup = 30;

    public DelayedPickupExperienceOrbEntity(World world, double x, double y, double z, int amount) {
        super(world, x, y, z, amount);
    }

    public DelayedPickupExperienceOrbEntity(EntityType<? extends ExperienceOrbEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if (ticksUntilPickup > 0) ticksUntilPickup--;
        super.tick();
    }

    @Override
    protected boolean isMergeable(ExperienceOrbEntity other) {
        return ticksUntilPickup <= 0 && super.isMergeable(other);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (ticksUntilPickup <= 0) super.onPlayerCollision(player);
    }
}
