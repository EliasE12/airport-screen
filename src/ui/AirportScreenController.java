package ui;

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

public class AirportScreenController implements Initializable {

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

    private Airport airport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        airport = new Airport();
        cbCriteria.getItems().addAll("DATE","TIME","AIRLINE","FLIGTH","CITY","GATE","STATE");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Welcome to the airport screen");
        dialog.setHeaderText("Enter the number of flights");
        dialog.setContentText("Please enter the number of flights");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            int numberFligths = Integer.parseInt(result.get());
            airport.genereteRandomFligths(numberFligths);
        }

        tcDate.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Date"));
        tcTime.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Departure Time"));
        tcAirline.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Airline"));
        tcFligth.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Fligth"));
        tcCity.setCellValueFactory(new PropertyValueFactory<Fligth,String>("City"));
        tcGate.setCellValueFactory(new PropertyValueFactory<Fligth,Integer>("Gate"));
        tcState.setCellValueFactory(new PropertyValueFactory<Fligth,String>("State"));

        tvScreen.getItems().addAll(airport.observableFligths());

    }

    @FXML
    void controlBtBack(ActionEvent event) {}
    @FXML
    void controlBtNext(ActionEvent event) {}


    @FXML
    void controlBtBinarySearch(ActionEvent event) {
        String criteria = cbCriteria.getValue();
        String search = tfsearch.getText();
        try {
           if (airport.searchByBinarySearch(criteria,search) != null){

           }else{
               throw new NullPointerException();
           }
       }catch (NullPointerException e){
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("Flight not found");
            men.setContentText("The flight looking does not exist.");
            men.showAndWait();
        }
    }

    @FXML
    void controlBtSequentialSearch(ActionEvent event) {
        String criteria = cbCriteria.getValue();
        String search = tfsearch.getText();
        try {
            if (airport.searchBySequentialSearch(criteria,search) != null){

            }else{
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Warning !!!");
            men.setHeaderText("Flight not found");
            men.setContentText("The flight looking does not exist.");
            men.showAndWait();
        }
    }


    @FXML
    void controlBtSortByAirline(ActionEvent event) {
      airport.sortByAirline();
    }

    @FXML
    void controlBtSortByCity(ActionEvent event) {
        airport.sortByCity();
    }

    @FXML
    void controlBtSortByDate(ActionEvent event) {
        airport.sortByNaturalOrder();
    }

    @FXML
    void controlBtSortByTime(ActionEvent event) {
        airport.sortByTime();
    }

    @FXML
    void controlBtSortByFligth(ActionEvent event) {
        airport.sortByFligth();
    }

    @FXML
    void controlBtSortByGate(ActionEvent event) {
        airport.sortByGate();
    }

    @FXML
    void controlBtSortByState(ActionEvent event) {
        airport.sortByState();
    }


}
