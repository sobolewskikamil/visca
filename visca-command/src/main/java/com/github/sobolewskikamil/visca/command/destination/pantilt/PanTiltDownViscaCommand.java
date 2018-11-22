package com.github.sobolewskikamil.visca.command.destination.pantilt;

import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;

import java.util.function.Supplier;

public class PanTiltDownViscaCommand extends ViscaDestinationCommand {
    private static final Supplier<byte[]> commandSupplier = () -> new byte[]{1, 6, 1, 1, 2, 3, 2};

    private final byte panSpeed;
    private final byte tiltSpeed;

    public PanTiltDownViscaCommand(byte panSpeed, byte tiltSpeed) {
        this.panSpeed = panSpeed;
        this.tiltSpeed = tiltSpeed;
    }

    protected byte[] createCommand() {
        byte[] command = commandSupplier.get();
        command[3] = panSpeed;
        command[4] = tiltSpeed;
        return command;
    }
}
