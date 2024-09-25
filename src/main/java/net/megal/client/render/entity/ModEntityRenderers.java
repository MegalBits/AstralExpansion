package net.megal.client.render.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.megal.entity.ModEntityTypes;
import net.minecraft.client.render.entity.ExperienceOrbEntityRenderer;
import net.minecraft.entity.EntityType;

public class ModEntityRenderers {

    public static void initialize() {
        EntityRendererRegistry.register(ModEntityTypes.DELAYED_PICKUP_EXPERIENCE_ORB, ExperienceOrbEntityRenderer::new);
    }
}
