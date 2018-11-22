package com.github.sobolewskikamil.visca.communication;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

class ViscaResponseReader {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final SerialPort serialPort;

    ViscaResponseReader(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    ViscaResponse readResponse() {
        try {
            Future<Byte[]> future = executor.submit(new ByteResponseReader(serialPort));
            return bytesToResponse(future.get(5, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            return ViscaResponse.TIMEOUT;
        }
    }

    private ViscaResponse bytesToResponse(Byte[] bytes) {
        byte typeByte = bytes[1];
        if (typeByte >= 0x30 && typeByte < 0x40) {
            return ViscaResponse.CHANGED;
        }
        if (typeByte >= 0x40 && typeByte < 0x50) {
            return ViscaResponse.ACK;
        }
        if (typeByte >= 0x50 && typeByte < 0x60) {
            return ViscaResponse.COMPLETION;
        }
        return ViscaResponse.ERROR;
    }

    private static class ByteResponseReader implements Callable<Byte[]> {
        private final SerialPort serialPort;

        private ByteResponseReader(SerialPort serialPort) {
            this.serialPort = serialPort;
        }

        @Override
        public Byte[] call() throws Exception {
            List<Byte> data = new LinkedList<>();
            while (true) {
                while (hasMoreBytesInBuffer()) {
                    byte readByte = serialPort.readBytes(1)[0];
                    data.add(readByte);
                    if (isEnd(readByte)) {
                        return data.toArray(new Byte[0]);
                    }
                }
            }
        }

        private boolean isEnd(byte firstByte) {
            return firstByte == -1;
        }

        private boolean hasMoreBytesInBuffer() throws SerialPortException {
            return serialPort.getInputBufferBytesCount() != 0;
        }
    }
}
