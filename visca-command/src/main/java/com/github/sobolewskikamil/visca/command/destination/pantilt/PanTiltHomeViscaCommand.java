package com.github.sobolewskikamil.visca.command.destination.pantilt;

import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;

import java.util.function.Supplier;

public class PanTiltHomeViscaCommand extends ViscaDestinationCommand {
    private static final Supplier<byte[]> commandSupplier = () -> new byte[]{1, 6, 4};

    protected byte[] createCommand() {
        return commandSupplier.get();
    }
}
