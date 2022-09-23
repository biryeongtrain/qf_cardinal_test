package net.biryeongtrain06.qf_stat_mod.entity;

import net.biryeongtrain06.qf_stat_mod.util.DamageSourceAdder;
import net.biryeongtrain06.qf_stat_mod.util.enums.Elements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class onPlayerDamageToEntity {
    public static void applyDamage(Entity target, PlayerEntity source, DamageSource dmgSource, float damage, Elements element) {
        if (target.getWorld().isClient) {
            return;
        }
        if (target.isPlayer()) {
            return;
        }
        if (!target.isAlive()) {
            return;
        }
        String defenseKey = element.resistanceStat.key;

        DamageSourceAdder damageSource = new DamageSourceAdder(dmgSource, source, element, damage);
        target.damage(damageSource, damageSource.realDamage);


        //TODO - 데미지 주는거랑 방어력에 따른 데미지 감소 / 회피 넣어아함
    }
}
