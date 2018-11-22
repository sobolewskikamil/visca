package com.github.sobolewskikamil.visca.command.destination.utils;

import java.util.StringJoiner;

public final class ByteArrayUtils {
    private ByteArrayUtils() {
    }

    public static String byteArrayToString(byte[] bytes) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (byte b : bytes) {
            stringJoiner.add(String.format("%02X", b));
        }
        return stringJoiner.toString();
    }
}
