package clockThread;

import userInterface.AirportScreenGUIController;

// Class
public class ClockThread extends Thread {

    // Atribute
    private AirportScreenGUIController controller;

    // Constructor
    public ClockThread(AirportScreenGUIController controller){
        this.controller = controller;
    }

    // Methods
    public void run(){
            controller.updateClock();
    }

}
