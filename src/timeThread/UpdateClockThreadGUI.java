package timeThread;

import javafx.application.Platform;
import userInterface.AirportScreenController;

// Class
public class UpdateClockThreadGUI extends Thread {

    // Constant
    private final static long UPDATE_TIME = 1000;

    // Atribute
    private AirportScreenController controller;

    // Constructor
    public UpdateClockThreadGUI(AirportScreenController controller){
        this.controller = controller;
    }

    // Methods
    public void run(){
        while (true) {
            ClockThread ct = new ClockThread(controller);
            Platform.runLater(ct);

            try {
                Thread.sleep(UPDATE_TIME);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }


}
