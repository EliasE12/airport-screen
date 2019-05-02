package clockThread;

import javafx.application.Platform;
import userInterface.AirportScreenGUIController;

// Class
public class UpdateClockThreadGUI extends Thread {

    // Constant
    private final static long UPDATE_TIME = 1000;

    // Atribute
    private AirportScreenGUIController controller;

    // Constructor
    public UpdateClockThreadGUI(AirportScreenGUIController controller){
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
