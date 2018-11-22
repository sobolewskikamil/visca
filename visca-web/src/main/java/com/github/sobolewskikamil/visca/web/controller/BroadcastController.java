package com.github.sobolewskikamil.visca.web.controller;

import com.github.sobolewskikamil.visca.command.broadcast.ViscaBroadcastCommand;
import com.github.sobolewskikamil.visca.command.broadcast.misc.AutoAssignAddressViscaCommand;
import com.github.sobolewskikamil.visca.communication.ViscaResponse;
import com.github.sobolewskikamil.visca.web.request.CommandRequest;
import com.github.sobolewskikamil.visca.web.service.CommandExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/broadcast")
public class BroadcastController {
    private final CommandExecutionService executionService;

    @Autowired
    public BroadcastController(CommandExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/misc/autoassign")
    public List<ViscaResponse> autoAssign(@RequestBody CommandRequest request) {
        ViscaBroadcastCommand command = new AutoAssignAddressViscaCommand();
        return executionService.executeBroadcastCommand(request.getSource(), command);
    }
}
