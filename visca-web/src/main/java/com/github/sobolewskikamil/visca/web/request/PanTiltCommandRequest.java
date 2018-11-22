package com.github.sobolewskikamil.visca.web.request;

public class PanTiltCommandRequest extends CommandRequest {
    private byte panSpeed;
    private byte tiltSpeed;

    public byte getPanSpeed() {
        return panSpeed;
    }

    public void setPanSpeed(byte panSpeed) {
        this.panSpeed = panSpeed;
    }

    public byte getTiltSpeed() {
        return tiltSpeed;
    }

    public void setTiltSpeed(byte tiltSpeed) {
        this.tiltSpeed = tiltSpeed;
    }
}
