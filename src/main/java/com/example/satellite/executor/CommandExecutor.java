package com.example.satellite.executor;

import com.example.satellite.Satellite;
import com.example.satellite.commands.Command;
import com.example.satellite.exceptions.SatelliteException;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CommandExecutor {
    private static final Logger LOG = Logger.getLogger(CommandExecutor.class.getName());
    private final Satellite satellite;

    public CommandExecutor(Satellite satellite) {
        this.satellite = Objects.requireNonNull(satellite);
    }

    public void executeAll(List<Command> commands) {
        for (Command cmd : commands) {
            try {
                LOG.log(Level.INFO, "Executing command: {0}", cmd);
                cmd.execute(satellite);
            } catch (SatelliteException e) {
                LOG.log(Level.SEVERE, "Command failed: {0}", e.toString());
            }
        }
    }
}