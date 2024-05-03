/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.mixin;

import meteordevelopment.meteorclient.systems.config.Config;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    private boolean override = true;
    private final Random random = new Random();

    private final List<String> meteorSplashes = getMeteorSplashes();

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<SplashTextRenderer> cir) {
        if (Config.get() == null || !Config.get().titleScreenSplashes.get()) return;

        if (override) cir.setReturnValue(new SplashTextRenderer(meteorSplashes.get(random.nextInt(meteorSplashes.size()))));
        override = !override;
    }

    private static List<String> getMeteorSplashes() {
        return Arrays.asList(
                "#1 mod for porn addicts",
                "more normal than hyperdimension episode 8",
                "less (?) normal than evangelion",
                "based utility mod (now 20% more!!!)",
                "friendly sliding session (no switches)",
                "100% filled with cum uuuooohhhhh",
                ":3",
                "goooood morning catboys!!! :3",
                "0% naked 14 year olds (no kaworu, sorry fellas)",
                "imagine an apple",
                ">w<",
                "just use the resource pack splashes"
        );
    }

}
