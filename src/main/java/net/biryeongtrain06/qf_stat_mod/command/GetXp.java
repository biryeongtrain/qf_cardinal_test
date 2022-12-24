package net.biryeongtrain06.qf_stat_mod.command;

import com.mojang.brigadier.context.CommandContext;
import net.biryeongtrain06.qf_stat_mod.api.PlayerStat;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.biryeongtrain06.qf_stat_mod.api.DataStorage.PLAYER_STAT_DATA_STORAGE;

public class GetXp {
    public static int getXP(CommandContext<ServerCommandSource> objectCommandContext) {
        try {
            ServerPlayerEntity player = objectCommandContext.getSource().getPlayer();
            PlayerStat playerStat = PLAYER_STAT_DATA_STORAGE.load(player);
            player.sendMessage(Text.literal("XP : " + playerStat.getXP()));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
