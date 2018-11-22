package com.github.sobolewskikamil.visca.cli;

import com.github.sobolewskikamil.visca.command.destination.utils.ByteArrayUtils;
import com.github.sobolewskikamil.visca.communication.ViscaCommandExecutor;
import jssc.SerialPort;

import java.io.InputStream;
import java.util.Scanner;

public class ViscaCLI {
    private final ViscaCommandParserFacade parserFacade = new ViscaCommandParserFacade();
    private final Scanner scanner;
    private final SerialPort serialPort;

    private ViscaCLI(InputStream inputStream, String comm) {
        this.scanner = new Scanner(inputStream);
        this.serialPort = new SerialPort(comm);
    }

    public static void main(String[] args) {
        String comm = args[0];
        ViscaCLI viscaCli = new ViscaCLI(System.in, comm);

        String srcAddr = args[1];
        viscaCli.start(srcAddr);
    }

    private void start(String sourceAddress) {
        byte source = Byte.parseByte(sourceAddress);

        try (ViscaCommandExecutor executor = new ViscaCommandExecutor(serialPort)) {
            while (true) {
                System.out.println("Enter commands:");
                String commandString = scanner.nextLine();

                byte[] command = parserFacade.parseToCommand(commandString).apply(source);
                System.out.println(String.format("Executing: %s", ByteArrayUtils.byteArrayToString(command)));

                executor.executeCommand(command).forEach(System.out::println);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
