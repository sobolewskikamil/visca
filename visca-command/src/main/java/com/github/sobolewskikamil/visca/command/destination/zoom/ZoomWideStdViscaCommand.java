package com.github.sobolewskikamil.visca.command.destination.zoom;

import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;

import java.util.function.Supplier;

public class ZoomWideStdViscaCommand extends ViscaDestinationCommand {
    private static final Supplier<byte[]> commandSupplier = () -> new byte[]{1, 4, 7, 3};

    private final byte speed;

    public ZoomWideStdViscaCommand(byte speed) {
        this.speed = speed;
    }

    protected byte[] createCommand() {
        byte[] command = commandSupplier.get();
        command[3] = (byte) (30 + speed);
        return command;
    }
}
