package com.github.sobolewskikamil.visca.command.broadcast;

import com.github.sobolewskikamil.visca.command.ViscaCommand;

public abstract class ViscaBroadcastCommand extends ViscaCommand<BroadcastRequest> {

    protected abstract byte[] createCommand();

    @Override
    public byte[] createInvokableCommand(BroadcastRequest request) {
        byte[] command = createCommand();
        byte head = createBroadcastHead(request.getSourceAddress());
        return createInvokableCommand(head, command);
    }

    private byte createBroadcastHead(byte sourceAddress) {
        return (byte) (128 | (sourceAddress & 7) << 4 | 8 & 15);
    }
}
