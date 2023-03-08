package net.biryeongtrain06.qf_stat_mod.stats.interfaces;

import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.biryeongtrain06.qf_stat_mod.utils.TextHelper;
import net.biryeongtrain06.qf_stat_mod.utils.enums.StatSubTag;
import net.minecraft.util.Identifier;

public interface IStats {
    default Identifier getBaseStatId() {
        return TextHelper.getId("base_value");
    }
    void addStatInstance(Identifier id, float value, StatSubTag tag);
    float getTotalValue();
    boolean tryReplaceInstance(Identifier id, float value, StatSubTag tag);
    boolean removeStatInstance(Identifier id, StatSubTag tag);
    boolean hasInstance(Identifier id, StatSubTag tag);
    float getInstanceValueById(Identifier id, StatSubTag tag);
    Object2FloatOpenHashMap<Identifier> getCloneInstances(StatSubTag tag);
}
