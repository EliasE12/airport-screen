package userInterface;

import exceptions.FlitgthNoExistException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airport;
import model.Fligth;
import model.Airport.Criteria;
import timeThread.UpdateClockThreadGUI;
import java.net.URL;
import java.util.*;

// Class
public class AirportScreenController implements Initializable {

    // Atributes
    @FXML private TableView<Fligth> tvScreen;
    @FXML private TableColumn<Fligth, String> tcDate;
    @FXML private TableColumn<Fligth, String> tcTime;
    @FXML private TableColumn<Fligth, String> tcAirline;
    @FXML private TableColumn<Fligth, String> tcFligth;
    @FXML private TableColumn<Fligth, String> tcCity;
    @FXML private TableColumn<Fligth, Integer> tcGate;
    @FXML private TableColumn<Fligth, String> tcState;
    @FXML private ComboBox<String> cbCriteria;
    @FXML private TextField tfsearch;
    @FXML private Button btBack;
    @FXML private Button btNext;
    @FXML private Label lbTimeSearch;
    @FXML private Label lbClock;

    private int pageNumber;

    // Relation with de model
    private Airport airport;

    // Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        airport = new Airport();
        pageNumber =0;
        cbCriteria.getItems().addAll("DATE", "TIME", "AIRLINE", "FLIGTH", "CITY", "GATE", "STATE");

        UpdateClockThreadGUI clockThreadGUI = new UpdateClockThreadGUI(this);
        clockThreadGUI.start();

        tcDate.setCellValueFactory(new PropertyValueFactory<Fligth, String>("Date"));
        tcTime.setCellValueFactory(new PropertyValueFactory<Fligth, String>("Time"));
        tcAirline.setCellValueFactory(new PropertyValueFactory<Fligth, String>("Airline"));
        tcFligth.setCellValueFactory(new PropertyValueFactory<Fligth, String>("Fligth"));
        tcCity.setCellValueFactory(new PropertyValueFactory<Fligth, String>("City"));
        tcGate.setCellValueFactory(new PropertyValueFactory<Fligth, Integer>("Gate"));
        tcState.setCellValueFactory(new PropertyValueFactory<Fligth, String>("State"));
    }

    @FXML
    void controlBtCreateFligthsList(ActionEvent event) {

        airport.getFligths().clear();
        tvScreen.getItems().clear();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Welcome to the airport screen");
        dialog.setHeaderText("Enter the number of flights");
        dialog.setContentText("Please enter the number of flights");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                if (result.get().equals("")) {
                    throw new NullPointerException();
                } else {
                    int numberFligths = Integer.parseInt(result.get());
                    airport.generateRandomFligths(numberFligths);
                    airport.sortByDate();
                    printFligth();
                }
            } catch (NullPointerException e) {
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Warning !!!");
                men.setHeaderText("Worthless");
                men.setContentText("You have not entered any value.");
                men.showAndWait();
            } catch (NegativeArraySizeException e) {
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Warning !!!");
                men.setHeaderText("Negative number");
                men.setContentText("You must enter a positive integer.");
                men.showAndWait();
            }catch (NumberFormatException e){
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Warning !!!");
                men.setHeaderText("No number");
                men.setContentText("You must enter a positive integer.");
                men.showAndWait();
            }
        }
    }

    public void printFligth() {
        int[] pages = airport.createPage(pageNumber);
        if (pages[0]==0){
            btBack.setDisable(true);
        }else{
            btBack.setDisable(false);
        }
        if (pages[1]==airport.getFligths().size()){
            btNext.setDisable(true);
        }else {
            btNext.setDisable(false);
        }

        tvScreen.getItems().clear();
        ObservableList<Fligth> fligthsList = FXCollections.observableArrayList(airport.getFligths().subList(airport.createPage(pageNumber)[0],airport.createPage(pageNumber)[1]));
        tvScreen.getItems().addAll(fligthsList);
    }


    @FXML
    void controlBtBack(ActionEvent event) {
        pageNumber--;
        printFligth();
    }

    @FXML
    void controlBtNext(ActionEvent event) {
        pageNumber++;
        printFligth();
    }

    @FXML
    void controlBtBinarySearch(ActionEvent event) {
        String value = cbCriteria.getValue();
        Criteria criteria = null;
        switch (value){
            case "DATE":
               criteria = Criteria.DATE;
               break;
            case "TIME":
                criteria = Criteria.TIME;
                break;
            case "AIRLINE":
                criteria = Criteria.AIRLINE;
                break;
            case "FLIGTH":
                criteria = Criteria.FLIGTH;
                break;
            case "CITY":
                criteria = Criteria.CITY;
                break;
            case "GATE":
                criteria = Criteria.GATE;
                break;
            case "STATE":
                criteria = Criteria.STATE;
        }

        String search = tfsearch.getText();
        try {
            if (tfsearch.getText().equals("")) {
                throw new NullPointerException();
            } else {
                tvScreen.getItems().clear();
                tvScreen.getItems().addAll(airport.searchByBinarySearch(criteria, search));
                //showFlightDetails(airport.searchByBinarySearch(criter, search));
                long time = airport.getTimeSearch();
                lbTimeSearch.setText(time + "");
            }
        } catch (NullPointerException e) {
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("Worthless");
            men.setContentText("You have not entered any value.");
            men.showAndWait();
        } catch (FlitgthNoExistException e) {
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("Flight not found");
            men.setContentText("The flight looking does not exist.");
            men.showAndWait();
        } catch (NumberFormatException e){
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("No number");
            men.setContentText("You must enter a positive integer.");
            men.showAndWait();
        }
        btBack.setDisable(true);
        btNext.setDisable(true);
    }

    @FXML
    void controlBtSequentialSearch(ActionEvent event) {
        String value = cbCriteria.getValue();
        Criteria criteria = null;
        switch (value){
            case "DATE":
                criteria = Criteria.DATE;
                break;
            case "TIME":
                criteria = Criteria.TIME;
                break;
            case "AIRLINE":
                criteria = Criteria.AIRLINE;
                break;
            case "FLIGTH":
                criteria = Criteria.FLIGTH;
                break;
            case "CITY":
                criteria = Criteria.CITY;
                break;
            case "GATE":
                criteria = Criteria.GATE;
                break;
            case "STATE":
                criteria = Criteria.STATE;
        }

        String search = tfsearch.getText();
        try {
            if (tfsearch.getText().equals("")) {
                throw new NullPointerException();
            } else {
                tvScreen.getItems().clear();
                tvScreen.getItems().addAll(airport.searchBySequentialSearch(criteria, search));
                lbTimeSearch.setText(airport.getTimeSearch() + "");
            }
        } catch (NullPointerException e) {
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("Worthless");
            men.setContentText("You have not entered any value.");
            men.showAndWait();
        } catch (FlitgthNoExistException e) {
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("Flight not found");
            men.setContentText("The flight looking does not exist.");
            men.showAndWait();
        } catch (NumberFormatException e){
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("No number");
            men.setContentText("You must enter a positive integer.");
            men.showAndWait();
        }
        btBack.setDisable(true);
        btNext.setDisable(true);
    }


    @FXML
    void controlBtSortByAirline(ActionEvent event) {
        airport.sortByAirline();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }

    @FXML
    void controlBtSortByCity(ActionEvent event) {
        airport.sortByCity();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }

    @FXML
    void controlBtSortByDate(ActionEvent event) {
        airport.sortByDate();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }

    @FXML
    void controlBtSortByTime(ActionEvent event) {
        airport.sortByNaturalOrder();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }

    @FXML
    void controlBtSortByFligth(ActionEvent event) {
        airport.sortByFligth();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }

    @FXML
    void controlBtSortByGate(ActionEvent event) {
        airport.sortByGate();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }

    @FXML
    void controlBtSortByState(ActionEvent event) {
        airport.sortByState();
        tvScreen.getItems().clear();
        pageNumber = 0;
        printFligth();
    }


    public void updateClock(){
        Calendar calendar = Calendar.getInstance();
        String hour = calendar.get(Calendar.HOUR)+"";
        String minute = calendar.get(Calendar.MINUTE)+"";
        String secund = calendar.get(Calendar.SECOND)+"";

        if(Integer.parseInt(hour)<10)
            hour = "0"+hour;
        if (Integer.parseInt(minute)<10)
            minute = "0"+minute;
        if (Integer.parseInt(secund)<10)
            secund = "0"+secund;
        if (hour.equals("00"))
            hour = "12";
        lbClock.setText(" "+hour+":"+minute+":"+secund);
    }


}
