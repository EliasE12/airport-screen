package timeThread;

import ui.AirportScreenController;

public class ClockThread extends Thread {

    private AirportScreenController airportScreenController;

    public ClockThread(AirportScreenController airportScreenController){
        this.airportScreenController = airportScreenController;
    }

    public void run(){}


}
