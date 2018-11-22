package com.github.sobolewskikamil.visca.command.destination;

import com.github.sobolewskikamil.visca.command.ViscaRequest;

public class DestinationRequest implements ViscaRequest {
    private final byte sourceAddress;
    private final byte destinationAddress;

    private DestinationRequest(byte sourceAddress, byte destinationAddress) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
    }

    public static DestinationRequest of(byte sourceAddress, byte destinationAddress) {
        return new DestinationRequest(sourceAddress, destinationAddress);
    }

    byte getSourceAddress() {
        return sourceAddress;
    }

    byte getDestinationAddress() {
        return destinationAddress;
    }
}
