package com.example.satellite.commands;

import com.example.satellite.Satellite;
import com.example.satellite.exceptions.InactivePanelsException;

public class CollectDataCommand implements Command {

    @Override
    public void execute(Satellite satellite) throws InactivePanelsException {
        satellite.collectData();
    }

    @Override
    public String toString() {
        return "collectData()";
    }
}