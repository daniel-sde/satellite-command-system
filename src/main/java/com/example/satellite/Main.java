package com.example.satellite;

import com.example.satellite.commands.*;
import com.example.satellite.executor.CommandExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());

        Satellite satellite = new Satellite();
        CommandExecutor executor = new CommandExecutor(satellite);

        List<Command> commands = new ArrayList<>();
        commands.add(new RotateCommand(Orientation.SOUTH));
        commands.add(new ActivatePanelsCommand());
        commands.add(new CollectDataCommand());

        commands.add(new RotateCommand(Orientation.EAST));
        commands.add(new CollectDataCommand());

        commands.add(new RotateCommand(Orientation.NORTH));
        commands.add(new ActivatePanelsCommand());
        commands.add(new CollectDataCommand());

        commands.add(new RotateCommand(Orientation.WEST));
        commands.add(new DeactivatePanelsCommand());
        commands.add(new CollectDataCommand()); // This will throw error

        executor.executeAll(commands);

        logger.log(Level.INFO, "Final Satellite State: {0}", satellite);
    }
}