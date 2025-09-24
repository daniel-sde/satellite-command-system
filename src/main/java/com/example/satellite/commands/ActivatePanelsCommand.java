package com.example.satellite.commands;

import com.example.satellite.Satellite;

public class ActivatePanelsCommand implements Command {
    @Override
    public void execute(Satellite satellite) {
        satellite.activatePanels();
    }

    @Override
    public String toString() {
        return "activatePanels()";
    }
}