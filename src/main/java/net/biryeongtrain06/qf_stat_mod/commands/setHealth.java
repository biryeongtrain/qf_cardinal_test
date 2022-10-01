package net.biryeongtrain06.qf_stat_mod.commands;

import net.biryeongtrain06.qf_stat_mod.component.StatComponent;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class setHealth {
    public static int onExecuted(ServerPlayerEntity player, int value) {
        try {
            StatComponent.PLAYERSTAT.get(player).setMaxHealth(value);
            player.sendMessage(Text.literal("Now your Health is " + value));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
