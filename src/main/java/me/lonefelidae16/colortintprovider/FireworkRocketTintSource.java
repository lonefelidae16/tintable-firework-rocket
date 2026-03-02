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

public record FireworkRocketTintSource(int defaultColor) implements FireworkRocketTintSourceBase {
    public static final MapCodec<FireworkRocketTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codecs.RGB.fieldOf("default").forGetter(FireworkRocketTintSource::defaultColor)).apply(instance, FireworkRocketTintSource::new));

    @Override
    public IntList gatherColors(ItemStack itemStack) {
        FireworksComponent component = itemStack.get(DataComponentTypes.FIREWORKS);
        if (component == null) {
            return IntList.of(this.defaultColor);
        }

        final IntList intList = new IntArrayList();
        for (var explosion : component.explosions()) {
            intList.addAll(explosion.colors());
        }

        if (intList.isEmpty()) {
            return IntList.of(this.defaultColor);
        }

        return intList;
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}
