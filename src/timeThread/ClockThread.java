package timeThread;

import userInterface.AirportScreenController;

// Class
public class ClockThread extends Thread {

    // Atribute
    private AirportScreenController controller;

    // Constructor
    public ClockThread(AirportScreenController controller){
        this.controller = controller;
    }

    // Methods
    public void run(){
            controller.updateClock();
    }

}
