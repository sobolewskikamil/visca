package com.github.sobolewskikamil.visca.command.destination.misc;

import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;

import java.util.function.Supplier;

public class SetAddressViscaCommand extends ViscaDestinationCommand {
    private static final Supplier<byte[]> commandSupplier = () -> new byte[]{48, 1};

    private final byte address;

    public SetAddressViscaCommand(byte address) {
        this.address = address;
    }

    protected byte[] createCommand() {
        byte[] command = commandSupplier.get();
        command[1] = address;
        return command;
    }
}
