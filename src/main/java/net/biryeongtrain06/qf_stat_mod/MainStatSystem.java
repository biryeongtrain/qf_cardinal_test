package net.biryeongtrain06.qf_stat_mod;


import net.biryeongtrain06.qf_stat_mod.api.DataStorage;
import net.biryeongtrain06.qf_stat_mod.command.GameRuleKeys;
import net.biryeongtrain06.qf_stat_mod.data.MobLevelDataLoader;
import net.biryeongtrain06.qf_stat_mod.data.MobXpDataLoader;
import net.biryeongtrain06.qf_stat_mod.callback.CallbackInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainStatSystem implements ModInitializer {

    public static final String MOD_ID ="qf_stat_mod";
    final public static Logger debugLogger = LogManager.getLogger("Qf Stat Debug");



    @Override
    public void onInitialize() {
        DataStorage.register();
        CallbackInit.init();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new MobXpDataLoader());
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new MobLevelDataLoader());
        GameRuleKeys.setupGameRule();
    }
}


