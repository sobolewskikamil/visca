package com.github.sobolewskikamil.visca.web.request;

public class ZoomCommandRequest extends CommandRequest {
    private byte speed;

    public byte getSpeed() {
        return speed;
    }

    public void setSpeed(byte speed) {
        this.speed = speed;
    }
}
