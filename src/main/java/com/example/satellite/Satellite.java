package com.example.satellite;

import com.example.satellite.exceptions.InactivePanelsException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Satellite {
    private static final Logger LOG = Logger.getLogger(Satellite.class.getName());


    private Orientation orientation = Orientation.NORTH;
    private PanelStatus solarPanels = PanelStatus.INACTIVE;
    private int dataCollected = 0;

    public void rotate(Orientation newOrientation) {
        this.orientation = newOrientation;
        LOG.log(Level.INFO, "Rotated to: {0}", newOrientation);
    }


    public void activatePanels() {
        if (solarPanels.equals(PanelStatus.ACTIVE)) {
            LOG.warning("Solar panels already activated!!");
            return;
        }
        this.solarPanels = PanelStatus.ACTIVE;
        LOG.info("Solar panels activated");
    }


    public void deactivatePanels() {
        if (solarPanels.equals(PanelStatus.INACTIVE)) {
            LOG.warning("Solar panels already deactivated!!");
            return;
        }
        this.solarPanels = PanelStatus.INACTIVE;
        LOG.info("Solar panels deactivated");
    }


    public void collectData() throws InactivePanelsException {
        if (solarPanels != PanelStatus.ACTIVE) {
            throw new InactivePanelsException("Cannot collect data when panels are inactive");
        }
        this.dataCollected += 10;
        String formattedLog = String.format("Collected data: +10, total = %s", dataCollected);
        LOG.info(formattedLog);
    }


    @Override
    public String toString() {
        return "Orientation: " + orientation +
                ", Solar Panels: " + solarPanels +
                ", Data Collected: " + dataCollected;
    }
}
