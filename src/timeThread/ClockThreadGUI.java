package timeThread;

import ui.AirportScreenController;

public class ClockThreadGUI extends Thread {

    private AirportScreenController airportScreenController;

    public ClockThreadGUI(AirportScreenController airportScreenController){
        this.airportScreenController = airportScreenController;
    }

    public void run(){}


}
