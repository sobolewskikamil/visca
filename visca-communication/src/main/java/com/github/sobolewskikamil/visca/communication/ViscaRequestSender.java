package com.github.sobolewskikamil.visca.communication;

import jssc.SerialPort;
import jssc.SerialPortException;

class ViscaRequestSender {
    private final SerialPort serialPort;

    ViscaRequestSender(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    boolean send(byte[] command) {
        try {
            return serialPort.writeBytes(command);
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }
}
