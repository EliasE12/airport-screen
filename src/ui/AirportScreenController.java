package ui;

import exceptions.FlitgthNoExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airport;
import model.Fligth;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
    @FXML private Button btSequentialSearch;
    @FXML private Button btBinarysearch;
    @FXML private Button btSortByDate;
    @FXML private Button btSortByTime;
    @FXML private Button btSortByAirline;
    @FXML private Button btSortByFligth;
    @FXML private Button btSortByCity;
    @FXML private Button btSortByGate;
    @FXML private Button btSortByState;
    @FXML private Button btBack;
    @FXML private Button btNext;
    @FXML private Label lbTimeSearch;
    @FXML private Button btCreateFligthsList;
    @FXML private Label lbClock;


    // Relation with main class of model
    private Airport airport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        airport = new Airport();
        cbCriteria.getItems().addAll("DATE", "TIME", "AIRLINE", "FLIGTH", "CITY", "GATE", "STATE");

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
        airport.observableFligths().clear();
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
                    airport.genereteRandomFligths(numberFligths);
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
        tvScreen.getItems().addAll(airport.observableFligths());
    }

    @FXML
    void controlBtBack(ActionEvent event) {
    }

    @FXML
    void controlBtNext(ActionEvent event) {
    }

    @FXML
    void controlBtBinarySearch(ActionEvent event) {
        String criter = cbCriteria.getValue();
        String search = tfsearch.getText();
        try {
            if (tfsearch.getText().equals("")) {
                throw new NullPointerException();
            } else {
                tvScreen.getItems().clear();
                tvScreen.getItems().addAll(airport.searchByBinarySearch(criter, search));
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

    }

    @FXML
    void controlBtSequentialSearch(ActionEvent event) {
        String criter = cbCriteria.getValue();
        String search = tfsearch.getText();
        try {
            if (tfsearch.getText().equals("")) {
                throw new NullPointerException();
            } else {
                tvScreen.getItems().clear();
                tvScreen.getItems().addAll(airport.searchByBinarySearch(criter, search));
                //showFlightDetails(airport.searchBySequentialSearch(criter, search));
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

    }

    public void showFlightDetails(Fligth searched) {

        Alert men = new Alert(Alert.AlertType.INFORMATION);
        men.setTitle("Details");
        men.setHeaderText(" ");
        String details = "\n";

        details += "Date : " + searched.getDate() + "\n";
        details += "Time : " + searched.getTime() + "\n";
        details += "Airline : " + searched.getAirline() + "\n";
        details += "Fligth : " + searched.getFligth() + "\n";
        details += "City : " + searched.getCity() + "\n";
        details += "Gate : " + searched.getGate() + "\n";
        details += "State : " + searched.getState() + "\n";

        men.setContentText(details);
        men.show();
    }

    @FXML
    void controlBtSortByAirline(ActionEvent event) {
        airport.sortByAirline();
        tvScreen.getItems().clear();
        printFligth();
    }

    @FXML
    void controlBtSortByCity(ActionEvent event) {
        airport.sortByCity();
        tvScreen.getItems().clear();
        printFligth();
    }

    @FXML
    void controlBtSortByDate(ActionEvent event) {
        airport.sortByDate();
        tvScreen.getItems().clear();
        printFligth();
    }

    @FXML
    void controlBtSortByTime(ActionEvent event) {
        airport.sortByNaturalOrder();
        tvScreen.getItems().clear();
        printFligth();
    }

    @FXML
    void controlBtSortByFligth(ActionEvent event) {
        airport.sortByFligth();
        tvScreen.getItems().clear();
        printFligth();
    }

    @FXML
    void controlBtSortByGate(ActionEvent event) {
        airport.sortByGate();
        tvScreen.getItems().clear();
        printFligth();
    }

    @FXML
    void controlBtSortByState(ActionEvent event) {
        airport.sortByState();
        tvScreen.getItems().clear();
        printFligth();
    }


    public void upClockTreadGUI(){}


}
