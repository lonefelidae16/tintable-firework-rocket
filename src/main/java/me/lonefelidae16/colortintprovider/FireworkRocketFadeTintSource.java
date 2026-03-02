package me.lonefelidae16.colortintprovider;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;

public record FireworkRocketFadeTintSource(int defaultColor) implements FireworkRocketTintSourceBase {
    public static final MapCodec<FireworkRocketFadeTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codecs.RGB.fieldOf("default").forGetter(FireworkRocketFadeTintSource::defaultColor)).apply(instance, FireworkRocketFadeTintSource::new));

    @Override
    public IntList gatherColors(ItemStack itemStack) {
        FireworksComponent component = itemStack.get(DataComponentTypes.FIREWORKS);
        if (component == null) {
            return IntList.of(this.defaultColor);
        }

        final IntList fadeColors = new IntArrayList();
        final IntList mainColors = new IntArrayList();
        for (var explosion : component.explosions()) {
            fadeColors.addAll(explosion.fadeColors());
            mainColors.addAll(explosion.colors());
        }

        if (fadeColors.isEmpty()) {
            if (mainColors.isEmpty()) {
                return IntList.of(this.defaultColor);
            } else {
                return mainColors;
            }
        }

        return fadeColors;
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}
