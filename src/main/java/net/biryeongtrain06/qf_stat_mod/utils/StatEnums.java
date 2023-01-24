package net.biryeongtrain06.qf_stat_mod.utils;


import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public enum StatEnums {

    HEALTH("health", Formatting.RED),
    MANA("mana", Formatting.BLUE),
    ARMOR("armor", Formatting.WHITE),
    DODGE("dodge", Formatting.WHITE),
    FIRE_RESISTANCE("fire_resistance", Formatting.RED),
    WATER_RESISTANCE("water_resistance", Formatting.AQUA),
    EARTH_RESISTANCE("earth_resistance", Formatting.GREEN),
    LIGHT_RESISTANCE("light_resistance", Formatting.LIGHT_PURPLE),
    DARK_RESISTANCE("dark_resistance", Formatting.DARK_PURPLE);

    public final Text translatableName;
    public final Formatting format;
    StatEnums(String name, Formatting format) {
        this.translatableName = Text.translatable(TextHelper.createTranslation(name));
        this.format = format;
    }

    public Text getTranslatableName() {
        return translatableName;
    }

    public Formatting getFormat() {
        return format;
    }
}
