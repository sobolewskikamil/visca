package com.github.sobolewskikamil.visca.command;

public abstract class ViscaCommand<T extends ViscaRequest> {

    public abstract byte[] createInvokableCommand(T request);

    protected byte[] createInvokableCommand(byte head, byte[] command) {
        int invokableCommandLength = command.length + 2;
        byte[] invokableCommand = new byte[invokableCommandLength];
        System.arraycopy(command, 0, invokableCommand, 1, command.length);

        invokableCommand[0] = head;
        invokableCommand[invokableCommand.length - 1] = -1;

        return invokableCommand;
    }
}
