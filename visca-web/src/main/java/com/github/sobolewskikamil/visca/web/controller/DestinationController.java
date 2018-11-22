package com.github.sobolewskikamil.visca.web.controller;

import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;
import com.github.sobolewskikamil.visca.command.destination.misc.ClearAllViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.misc.SetAddressViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.pantilt.*;
import com.github.sobolewskikamil.visca.command.destination.zoom.ZoomTeleStdViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.zoom.ZoomWideStdViscaCommand;
import com.github.sobolewskikamil.visca.communication.ViscaResponse;
import com.github.sobolewskikamil.visca.web.request.CommandRequest;
import com.github.sobolewskikamil.visca.web.request.PanTiltCommandRequest;
import com.github.sobolewskikamil.visca.web.request.SetAddressCommandRequest;
import com.github.sobolewskikamil.visca.web.request.ZoomCommandRequest;
import com.github.sobolewskikamil.visca.web.service.CommandExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dest")
public class DestinationController {
    private final CommandExecutionService executionService;

    @Autowired
    public DestinationController(CommandExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/{dest}/misc/clearall")
    public List<ViscaResponse> clearAll(@PathVariable byte dest, @RequestBody CommandRequest request) {
        ViscaDestinationCommand command = new ClearAllViscaCommand();
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/misc/setaddress")
    public List<ViscaResponse> setAddress(@PathVariable byte dest, @RequestBody SetAddressCommandRequest request) {
        ViscaDestinationCommand command = new SetAddressViscaCommand(request.getAddress());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/pantilt/home")
    public List<ViscaResponse> panTiltHome(@PathVariable byte dest, @RequestBody CommandRequest request) {
        ViscaDestinationCommand command = new PanTiltHomeViscaCommand();
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/pantilt/down")
    public List<ViscaResponse> panTiltDown(@PathVariable byte dest, @RequestBody PanTiltCommandRequest request) {
        ViscaDestinationCommand command = new PanTiltDownViscaCommand(request.getPanSpeed(), request.getTiltSpeed());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/pantilt/up")
    public List<ViscaResponse> panTiltUp(@PathVariable byte dest, @RequestBody PanTiltCommandRequest request) {
        ViscaDestinationCommand command = new PanTiltUpViscaCommand(request.getPanSpeed(), request.getTiltSpeed());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/pantilt/left")
    public List<ViscaResponse> panTiltLeft(@PathVariable byte dest, @RequestBody PanTiltCommandRequest request) {
        ViscaDestinationCommand command = new PanTiltLeftViscaCommand(request.getPanSpeed(), request.getTiltSpeed());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/pantilt/right")
    public List<ViscaResponse> panTiltRight(@PathVariable byte dest, @RequestBody PanTiltCommandRequest request) {
        ViscaDestinationCommand command = new PanTiltRightViscaCommand(request.getPanSpeed(), request.getTiltSpeed());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/zoom/tele")
    public List<ViscaResponse> zoomTele(@PathVariable byte dest, @RequestBody ZoomCommandRequest request) {
        ViscaDestinationCommand command = new ZoomTeleStdViscaCommand(request.getSpeed());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }

    @PostMapping("/{dest}/zoom/wide")
    public List<ViscaResponse> zoomWide(@PathVariable byte dest, @RequestBody ZoomCommandRequest request) {
        ViscaDestinationCommand command = new ZoomWideStdViscaCommand(request.getSpeed());
        return executionService.executeDestinationCommand(request.getSource(), dest, command);
    }
}
