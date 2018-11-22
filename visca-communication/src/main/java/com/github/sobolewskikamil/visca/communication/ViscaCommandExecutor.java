package com.github.sobolewskikamil.visca.communication;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ViscaCommandExecutor implements AutoCloseable {
    private final SerialPort serialPort;
    private final ViscaRequestSender sender;
    private final ViscaResponseReader reader;

    public ViscaCommandExecutor(SerialPort serialPort) throws Exception {
        this.serialPort = serialPort;
        this.sender = new ViscaRequestSender(serialPort);
        this.reader = new ViscaResponseReader(serialPort);
        configureAndOpenPort(serialPort);
    }

    public List<ViscaResponse> executeCommand(byte[] command) {
        boolean result = sender.send(command);
        if (!result) {
            return Collections.singletonList(ViscaResponse.ERROR);
        }
        return readViscaResponses();
    }

    @Override
    public void close() throws Exception {
        serialPort.closePort();
    }

    private void configureAndOpenPort(SerialPort serialPort) throws SerialPortException {
        serialPort.openPort();
        serialPort.setParams(9600, 8, 1, 0);
    }

    private List<ViscaResponse> readViscaResponses() {
        ViscaResponse firstResponse = reader.readResponse();
        if (firstResponse == ViscaResponse.ACK) {
            return Arrays.asList(firstResponse, reader.readResponse());
        }
        return Collections.singletonList(firstResponse);
    }
}
