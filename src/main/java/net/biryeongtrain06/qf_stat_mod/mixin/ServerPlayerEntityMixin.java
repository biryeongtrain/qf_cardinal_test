package net.biryeongtrain06.qf_stat_mod.mixin;

import net.biryeongtrain06.qf_stat_mod.api.DataStorage;
import net.biryeongtrain06.qf_stat_mod.api.PlayerStat;
import net.biryeongtrain06.qf_stat_mod.player.IServerPlayerEntity;
import net.biryeongtrain06.qf_stat_mod.utils.TextHelper;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.security.auth.callback.Callback;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements IServerPlayerEntity {
    private boolean isPlayedBefore = false;
    private boolean isDisplaySystemMessage = true;

    @Inject(at = @At("RETURN"), method = ("writeCustomDataToNbt"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("isPlayedBefore", this.isPlayedBefore);
        nbt.putBoolean("isDisplaySystemMessage", this.isDisplaySystemMessage);
    }

    @Inject(at = @At("RETURN"), method = ("readCustomDataFromNbt"))
    public void readCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        this.isPlayedBefore = nbt.getBoolean("isPlayedBefore");
        this.isDisplaySystemMessage = nbt.getBoolean("isDisplaySystemMessage");
    }

    @Inject(at = @At("HEAD"), method = ("tick"))
    public void displayHealthBar(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        PlayerStat playerStat = DataStorage.loadPlayerStat(player);
        int healthPercent = (int) (playerStat.getCurrentHealth() / playerStat.getMaxHealth() * 100);
        player.sendMessage(
                Text.literal("\t\t\t")
                        .append(Text.translatable(TextHelper.createTranslation("health")).formatted(Formatting.RED))
                            .append(Text.literal(" : " + playerStat.getCurrentHealth()))
                        .formatted(
                                healthPercent >= 80 ?
                                        Formatting.GREEN : healthPercent >= 40 ?
                                        Formatting.GOLD : Formatting.RED
                        )
                        .append(Text.literal(" / " + playerStat.getMaxHealth()))
                ,true
        );
    }

    @Override
    public boolean isPlayedBefore() {
        return this.isPlayedBefore;
    }

    @Override
    public void setPlayedBefore(boolean val) {
        this.isPlayedBefore = val;
    }

    @Override
    public boolean isDisplaySystemMessage() {
        return this.isDisplaySystemMessage;
    }

    @Override
    public void setDisplaySystemMessage(boolean val) {
        this.isDisplaySystemMessage = val;
    }
}
