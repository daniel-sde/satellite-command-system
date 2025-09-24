package com.example.satellite.commands;

import com.example.satellite.Orientation;
import com.example.satellite.Satellite;

public class RotateCommand implements Command {
    private final Orientation orientation;

    public RotateCommand(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void execute(Satellite satellite) {
        satellite.rotate(orientation);
    }

    @Override
    public String toString() {
        return "rotate(" + orientation + ")";
    }
}