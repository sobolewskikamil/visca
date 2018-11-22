package com.github.sobolewskikamil.visca.command.broadcast.misc;

import com.github.sobolewskikamil.visca.command.broadcast.ViscaBroadcastCommand;

import java.util.function.Supplier;

public class AutoAssignAddressViscaCommand extends ViscaBroadcastCommand {
    private static final Supplier<byte[]> commandSupplier = () -> new byte[]{48, 1};

    protected byte[] createCommand() {
        return commandSupplier.get();
    }
}
