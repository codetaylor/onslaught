package com.codetaylor.mc.onslaught.modules.onslaught.factory;

import com.codetaylor.mc.onslaught.ModOnslaught;
import com.codetaylor.mc.onslaught.modules.onslaught.data.MobTemplateEffect;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.logging.Level;

/**
 * Responsible for applying mob template effects to an {@link EntityLiving}.
 */
public class MobTemplateEntityEffectApplicator {

  public void applyEffects(MobTemplateEffect[] effects, EntityLiving entity) {

    for (MobTemplateEffect mobTemplateEffect : effects) {
      this.applyEffect(mobTemplateEffect, entity);
    }
  }

  private void applyEffect(MobTemplateEffect effect, EntityLiving entity) {

    if (effect.id == null) {
      ModOnslaught.LOG.log(Level.SEVERE, "Missing mob template effect id");
      return;
    }

    ResourceLocation resourceLocation = new ResourceLocation(effect.id);
    Potion potion = ForgeRegistries.POTIONS.getValue(resourceLocation);

    if (potion == null) {
      ModOnslaught.LOG.log(Level.SEVERE, "Unknown mob template effect id: " + effect.id);
      return;
    }

    PotionEffect potionEffect = new PotionEffect(potion, effect.duration, effect.amplifier, false, effect.showParticles);
    entity.addPotionEffect(potionEffect);
  }
}
