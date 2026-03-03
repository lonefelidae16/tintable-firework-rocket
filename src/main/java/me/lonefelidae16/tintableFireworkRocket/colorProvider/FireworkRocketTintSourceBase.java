package me.lonefelidae16.tintableFireworkRocket.colorProvider;

import it.unimi.dsi.fastutil.ints.IntList;
import me.lonefelidae16.tintableFireworkRocket.TintableFireworkRocket;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.ColorHelper;
import org.jspecify.annotations.Nullable;

public interface FireworkRocketTintSourceBase extends TintSource {
    IntList gatherColors(ItemStack itemStack);

    @Override
    default int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        final IntList colors = gatherColors(stack);

        int colorLength = colors.size();
        if (colorLength == 0) {
            TintableFireworkRocket.LOGGER.error("colors.size() == 0");
            return 0;
        } else if (colorLength == 1) {
            return ColorHelper.fullAlpha(colors.getInt(0));
        } else {
            int redSum = 0;
            int blueSum = 0;
            int greenSum = 0;

            for (int i = 0; i < colorLength; ++i) {
                int color = colors.getInt(i);
                redSum += ColorHelper.getRed(color);
                blueSum += ColorHelper.getGreen(color);
                greenSum += ColorHelper.getBlue(color);
            }

            return ColorHelper.getArgb(redSum / colorLength, blueSum / colorLength, greenSum / colorLength);
        }
    }
}
