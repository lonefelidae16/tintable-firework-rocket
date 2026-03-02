package me.lonefelidae16.colortintprovider.mixin;

import me.lonefelidae16.colortintprovider.FireworkRocketFadeTintSource;
import me.lonefelidae16.colortintprovider.FireworkRocketTintSource;
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
		TintSourceTypes.ID_MAPPER.put(Identifier.ofVanilla("firework_rocket_main"), FireworkRocketTintSource.CODEC);
		TintSourceTypes.ID_MAPPER.put(Identifier.ofVanilla("firework_rocket_fade"), FireworkRocketFadeTintSource.CODEC);
	}
}