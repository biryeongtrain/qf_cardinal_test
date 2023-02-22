package net.biryeongtrain06.qf_stat_mod.player;

import net.biryeongtrain06.qf_stat_mod.api.DataStorage;
import net.biryeongtrain06.qf_stat_mod.api.PlayerStat;
import net.biryeongtrain06.qf_stat_mod.utils.enums.StatTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class PlayerHeadInfo {

    final ServerPlayerEntity player;

    public PlayerHeadInfo(ServerPlayerEntity player) {
        this.player = player;
    }

    public ItemStack getHead() {
        ItemStack stack = new ItemStack(Items.PLAYER_HEAD);
        stack.setCustomName(player.getDisplayName());
        NbtCompound ownerTag = stack.getOrCreateSubNbt(SkullItem.SKULL_OWNER_KEY);
        ownerTag.putUuid("Id", player.getUuid());
        setHeadLore(stack);
        return stack;
    }

    private void setHeadLore(ItemStack stack) {
        PlayerStat playerStat = DataStorage.loadPlayerStat(player);
        NbtList lore = new NbtList();
        NbtCompound itemNBT = stack.getOrCreateSubNbt(ItemStack.DISPLAY_KEY);
        lore.add(NbtString.of(Text.Serializer.toJson(Text.empty()
                .append(StatTypes.HEALTH_FLAT.getTranslatableName())
                .append(Text.literal(" : " + playerStat.getMaxHealth())))
                .formatted(StatTypes.HEALTH_FLAT.getFormat())));
        lore.add(NbtString.of(Text.Serializer.toJson(Text.empty()
                    .append(StatTypes.MANA.getTranslatableName())
                    .append(Text.literal(" : " + playerStat.getMaxMana()))
                    .formatted(StatTypes.MANA.getFormat()))));
        lore.add(NbtString.of(Text.Serializer.toJson(Text.empty()
                .append(StatTypes.ARMOR.getTranslatableName())
                .append(Text.literal(" : " + playerStat.getArmor()))
                .formatted(StatTypes.ARMOR.getFormat()))));
        lore.add(NbtString.of(Text.Serializer.toJson(Text.empty()
                .append(StatTypes.DODGE.getTranslatableName())
                .append(Text.literal(" : " + playerStat.getDodge()))
                .formatted(StatTypes.DODGE.getFormat()))));
        lore.add(NbtString.of(Text.Serializer.toJson(Text.empty()
                .append(StatTypes.SELECT_POINT.getTranslatableName())
                .append(Text.literal(" : " + playerStat.getSelectPoint()))
                .formatted(StatTypes.SELECT_POINT.getFormat()))));
        itemNBT.put(ItemStack.LORE_KEY, lore);

    }
}
