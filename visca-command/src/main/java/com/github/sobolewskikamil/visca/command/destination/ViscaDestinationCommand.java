package com.github.sobolewskikamil.visca.command.destination;

import com.github.sobolewskikamil.visca.command.ViscaCommand;

public abstract class ViscaDestinationCommand extends ViscaCommand<DestinationRequest> {

    protected abstract byte[] createCommand();

    @Override
    public byte[] createInvokableCommand(DestinationRequest request) {
        byte[] command = createCommand();
        byte head = createDestinationHead(request.getSourceAddress(), request.getDestinationAddress());
        return createInvokableCommand(head, command);
    }

    private byte createDestinationHead(byte sourceAddress, byte destinationAddress) {
        return (byte) (128 | (sourceAddress & 7) << 4 | destinationAddress & 15);
    }
}
