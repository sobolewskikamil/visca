package com.github.sobolewskikamil.visca.web.request;

public class SetAddressCommandRequest extends CommandRequest {
    private byte address;

    public byte getAddress() {
        return address;
    }

    public void setAddress(byte address) {
        this.address = address;
    }
}
