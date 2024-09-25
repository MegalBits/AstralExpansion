package net.megal.entity;

import net.megal.AstralExpansion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<DelayedPickupExperienceOrbEntity> DELAYED_PICKUP_EXPERIENCE_ORB =
            register(
                    "delayed_pickup_experience_orb",
                    EntityType.Builder.<DelayedPickupExperienceOrbEntity>create(DelayedPickupExperienceOrbEntity::new, SpawnGroup.MISC)
                            .dimensions(0.5F, 0.5F)
                            .maxTrackingRange(6)
                            .trackingTickInterval(20)
                            .build()
            );

    public static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(AstralExpansion.MOD_ID, id), entityType);
    }

    public static void initialize() {}
}
