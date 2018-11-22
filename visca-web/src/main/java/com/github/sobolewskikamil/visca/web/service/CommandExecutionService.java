package com.github.sobolewskikamil.visca.web.service;

import com.github.sobolewskikamil.visca.command.broadcast.BroadcastRequest;
import com.github.sobolewskikamil.visca.command.broadcast.ViscaBroadcastCommand;
import com.github.sobolewskikamil.visca.command.destination.DestinationRequest;
import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;
import com.github.sobolewskikamil.visca.communication.ViscaCommandExecutor;
import com.github.sobolewskikamil.visca.communication.ViscaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandExecutionService {
    @Autowired
    private ViscaCommandExecutor commandExecutor;

    public List<ViscaResponse> executeBroadcastCommand(byte source, ViscaBroadcastCommand command) {
        byte[] invokableCommand = command.createInvokableCommand(BroadcastRequest.of(source));
        return commandExecutor.executeCommand(invokableCommand);
    }

    public List<ViscaResponse> executeDestinationCommand(byte source, byte dest, ViscaDestinationCommand command) {
        byte[] invokableCommand = command.createInvokableCommand(DestinationRequest.of(source, dest));
        return commandExecutor.executeCommand(invokableCommand);
    }
}
