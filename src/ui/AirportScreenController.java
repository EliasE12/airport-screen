package ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.*;

public class AirportScreenController implements Initializable {

    @FXML private TableView<Fligth> tvScreen;
    @FXML private TableColumn<Fligth, String> tcDate;
    @FXML private TableColumn<Fligth, String> tcDepartureTime;
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
    @FXML private Button btSortByDepartureTime;
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
        cbCriteria.getItems().addAll("DATE","DEPARTURE_TIME","AIRLINE","CODE_FLIGTH","CITY","GATE","STATE");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Welcome to the airport screen");
        dialog.setHeaderText("Enter the number of flights");
        dialog.setContentText("Please enter the number of flights");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int numberFligths = Integer.parseInt(result.get());
            airport.genereteRandomFligths(numberFligths);
        }

        tcDate.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Date"));
        tcDepartureTime.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Departure Time"));
        tcAirline.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Airline"));
        tcFligth.setCellValueFactory(new PropertyValueFactory<Fligth,String>("Fligth"));
        tcCity.setCellValueFactory(new PropertyValueFactory<Fligth,String>("City"));
        tcGate.setCellValueFactory(new PropertyValueFactory<Fligth,Integer>("Gate"));
        tcState.setCellValueFactory(new PropertyValueFactory<Fligth,String>("State"));

        tvScreen.getItems().addAll(airport.observableFligths());

    }


    @FXML
    void controlBtBack(ActionEvent event) {

    }

    @FXML
    void controlBtBinarySearch(ActionEvent event) {

    }

    @FXML
    void controlBtNext(ActionEvent event) {

    }

    @FXML
    void controlBtSequentialSearch(ActionEvent event) {

    }

    @FXML
    void controlBtSortByAirline(ActionEvent event) {

    }

    @FXML
    void controlBtSortByCity(ActionEvent event) {

    }

    @FXML
    void controlBtSortByDate(ActionEvent event) {

    }

    @FXML
    void controlBtSortByDepartureTime(ActionEvent event) {

    }

    @FXML
    void controlBtSortByFligth(ActionEvent event) {

    }

    @FXML
    void controlBtSortByGate(ActionEvent event) {

    }

    @FXML
    void controlBtSortByState(ActionEvent event) {

    }


}
