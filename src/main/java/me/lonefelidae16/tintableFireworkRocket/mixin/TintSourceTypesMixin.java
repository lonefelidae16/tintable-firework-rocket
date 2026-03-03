package me.lonefelidae16.tintableFireworkRocket.mixin;

import me.lonefelidae16.tintableFireworkRocket.colorProvider.FireworkRocketFadeTintSource;
import me.lonefelidae16.tintableFireworkRocket.colorProvider.FireworkRocketTintSource;
import me.lonefelidae16.tintableFireworkRocket.TintableFireworkRocket;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TintSourceTypes.class)
public abstract class TintSourceTypesMixin {
	@Inject(method = "bootstrap", at = @At("HEAD"))
	private static void colorTintProvider$appendSource(CallbackInfo ci) {
		TintSourceTypes.ID_MAPPER.put(Identifier.of(TintableFireworkRocket.MOD_ID, "firework_rocket_main"), FireworkRocketTintSource.CODEC);
		TintSourceTypes.ID_MAPPER.put(Identifier.of(TintableFireworkRocket.MOD_ID, "firework_rocket_fade"), FireworkRocketFadeTintSource.CODEC);
	}
}