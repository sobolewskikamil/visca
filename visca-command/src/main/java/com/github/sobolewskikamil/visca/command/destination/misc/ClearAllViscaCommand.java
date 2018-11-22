package com.github.sobolewskikamil.visca.command.destination.misc;

import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;

import java.util.function.Supplier;

public class ClearAllViscaCommand extends ViscaDestinationCommand {
    private static final Supplier<byte[]> commandSupplier = () -> new byte[]{1, 0, 1};

    protected byte[] createCommand() {
        return commandSupplier.get();
    }
}
