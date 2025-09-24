package com.example.satellite.commands;


import com.example.satellite.Satellite;
import com.example.satellite.exceptions.SatelliteException;

public interface Command {
    void execute(Satellite satellite) throws SatelliteException;
}