package com.github.sobolewskikamil.visca.cli;

import com.github.sobolewskikamil.visca.command.ViscaCommand;
import com.github.sobolewskikamil.visca.command.broadcast.BroadcastRequest;
import com.github.sobolewskikamil.visca.command.broadcast.ViscaBroadcastCommand;
import com.github.sobolewskikamil.visca.command.broadcast.misc.AutoAssignAddressViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.DestinationRequest;
import com.github.sobolewskikamil.visca.command.destination.ViscaDestinationCommand;
import com.github.sobolewskikamil.visca.command.destination.misc.ClearAllViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.misc.SetAddressViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.pantilt.*;
import com.github.sobolewskikamil.visca.command.destination.zoom.ZoomTeleStdViscaCommand;
import com.github.sobolewskikamil.visca.command.destination.zoom.ZoomWideStdViscaCommand;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.function.Function;

class ViscaCommandParserFacade {

    Function<Byte, byte[]> parseToCommand(String commandString) {
        ViscaLexer lexer = new ViscaLexer(CharStreams.fromString(commandString));
        ViscaParser parser = new ViscaParser(new CommonTokenStream(lexer));
        ViscaInvokableCommandVisitor visitor = new ViscaInvokableCommandVisitor();
        ViscaParser.ParseContext parseCtx = parser.parse();
        visitor.visit(parseCtx);
        return visitor.invokableCommand;
    }

    private static class ViscaInvokableCommandVisitor extends ViscaBaseVisitor<ViscaCommand> {
        private Function<Byte, byte[]> invokableCommand;

        @Override
        public ViscaCommand visitDestCommand(ViscaParser.DestCommandContext ctx) {
            ViscaDestinationCommand command = visitDestCommands(ctx.destCommands());
            this.invokableCommand = src -> command.createInvokableCommand(DestinationRequest.of(src, tokenToByte(ctx.dest)));
            return command;
        }

        @Override
        public ViscaCommand visitBroadcastCommand(ViscaParser.BroadcastCommandContext ctx) {
            ViscaBroadcastCommand command = visitBroadcastCommands(ctx.broadcastCommands());
            this.invokableCommand = src -> command.createInvokableCommand(BroadcastRequest.of(src));
            return command;
        }

        @Override
        public ViscaBroadcastCommand visitBroadcastCommands(ViscaParser.BroadcastCommandsContext ctx) {
            ViscaCommand cmd = super.visitBroadcastCommands(ctx);
            if (cmd instanceof ViscaBroadcastCommand) {
                return (ViscaBroadcastCommand) cmd;
            }
            throw new IllegalStateException(String.format("Command %s is not supporting broadcasting", cmd));
        }

        @Override
        public ViscaBroadcastCommand visitAutoAssign(ViscaParser.AutoAssignContext ctx) {
            return new AutoAssignAddressViscaCommand();
        }

        @Override
        public ViscaDestinationCommand visitDestCommands(ViscaParser.DestCommandsContext ctx) {
            ViscaCommand cmd = super.visitDestCommands(ctx);
            if (cmd instanceof ViscaDestinationCommand) {
                return (ViscaDestinationCommand) cmd;
            }
            throw new IllegalStateException(String.format("Command %s is not supporting sending to specific destination", cmd));
        }

        @Override
        public ViscaDestinationCommand visitSetAddress(ViscaParser.SetAddressContext ctx) {
            return new SetAddressViscaCommand(tokenToByte(ctx.address));
        }

        @Override
        public ViscaDestinationCommand visitClearAllCommand(ViscaParser.ClearAllCommandContext ctx) {
            return new ClearAllViscaCommand();
        }

        @Override
        public ViscaDestinationCommand visitPanTiltDownCommand(ViscaParser.PanTiltDownCommandContext ctx) {
            return new PanTiltDownViscaCommand(tokenToByte(ctx.panspeed), tokenToByte(ctx.tiltspeed));
        }

        @Override
        public ViscaDestinationCommand visitPanTiltUpCommand(ViscaParser.PanTiltUpCommandContext ctx) {
            return new PanTiltUpViscaCommand(tokenToByte(ctx.panspeed), tokenToByte(ctx.tiltspeed));
        }

        @Override
        public ViscaDestinationCommand visitPanTiltRightCommand(ViscaParser.PanTiltRightCommandContext ctx) {
            return new PanTiltRightViscaCommand(tokenToByte(ctx.panspeed), tokenToByte(ctx.tiltspeed));
        }

        @Override
        public ViscaDestinationCommand visitPanTiltLeftCommand(ViscaParser.PanTiltLeftCommandContext ctx) {
            return new PanTiltLeftViscaCommand(tokenToByte(ctx.panspeed), tokenToByte(ctx.tiltspeed));
        }

        @Override
        public ViscaDestinationCommand visitPanTiltHomeCommand(ViscaParser.PanTiltHomeCommandContext ctx) {
            return new PanTiltHomeViscaCommand();
        }

        @Override
        public ViscaDestinationCommand visitZoomTeleCommand(ViscaParser.ZoomTeleCommandContext ctx) {
            return new ZoomTeleStdViscaCommand(tokenToByte(ctx.speed));
        }

        @Override
        public ViscaDestinationCommand visitZoomWideCommand(ViscaParser.ZoomWideCommandContext ctx) {
            return new ZoomWideStdViscaCommand(tokenToByte(ctx.speed));
        }

        private byte tokenToByte(Token token) {
            return Byte.parseByte(token.getText());
        }
    }
}
