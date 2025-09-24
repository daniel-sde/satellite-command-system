package com.example.satellite.commands;

import com.example.satellite.Satellite;

public class DeactivatePanelsCommand implements Command {
    @Override
    public void execute(Satellite satellite) {
        satellite.deactivatePanels();
    }

    @Override
    public String toString() {
        return "deactivatePanels()";
    }
}