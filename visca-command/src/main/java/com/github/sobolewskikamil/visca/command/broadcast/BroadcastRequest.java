package com.github.sobolewskikamil.visca.command.broadcast;

import com.github.sobolewskikamil.visca.command.ViscaRequest;

public class BroadcastRequest implements ViscaRequest {
    private final byte sourceAddress;

    private BroadcastRequest(byte sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public static BroadcastRequest of(byte sourceAddress) {
        return new BroadcastRequest(sourceAddress);
    }

    byte getSourceAddress() {
        return sourceAddress;
    }
}
