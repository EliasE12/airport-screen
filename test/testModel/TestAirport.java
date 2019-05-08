package testModel;

import exceptions.FlitgthNoExistException;
import javafx.collections.FXCollections;
import model.Airport;
import model.Airport.Criteria;
import model.Fligth;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestAirport {

/**
    private Airport airport;


    private void setupScenary1() {
        airport = null;
    }

    private List<Fligth> listFligth(){

        List<Fligth> fligths = new ArrayList<>();
        fligths.add(new Fligth("2019-05-12","12:23 PM","SATENA","NSE 2354","Cancúm",23,"Exit"));
        fligths.add(new Fligth("2019-05-07","09:13 AM","LANCO","L7 3647","London",17,"Exit"));
        fligths.add(new Fligth("2019-05-28","07:26 AM","AVIANCA","AVA 8553","Moscú",15,"Arrival"));
        fligths.add(new Fligth("2019-12-26","04:28 AM","TAMPA CARGO","QT 0235","París",21,"Exit"));
        fligths.add(new Fligth("2019-08-25","11:21 AM","WINGO","RPB 1253","London",9,"Arrival"));
        fligths.add(new Fligth("2019-09-12","03:07 AM","AVIANCA","AVA 4743","Chicago",6,"Exit"));
        fligths.add(new Fligth("2019-09-28","12:23 PM","SATENA","NSE 8794","Lima",11,"Arrival"));
        fligths.add(new Fligth("2019-04-16","08:14 AM","WINGO","RPB 2457","Roma",40,"Exit"));
        fligths.add(new Fligth("2019-07-09","06:23 AM","COPA AIRLINES","ARE 5467","Salem",4,"Arrival"));
        fligths.add(new Fligth("2019-11-24","06:54 AM","SATENA","NSE 4256","Bangladesh",3,"Exit"));
        fligths.add(new Fligth("2019-07-07","08:42 AM","LATAM","LA 8786","Madrid",12,"Arrival"));
        fligths.add(new Fligth("2019-06-30","10:08 AM","SATENA","NSE 6780","Cali",41,"Exit"));
        fligths.add(new Fligth("2019-09-16","12:55 PM","VIVA COLOMBIA","VPP 0535","Maimi",35,"Exit"));

        airport.setFligths(fligths);

        return listFligth();
    }

    private void setupScenary2() {
        airport = new Airport();
    }

    private void setupScenary3(){

        List<Fligth> fligths = new ArrayList<>();
        fligths.add(new Fligth("2019-05-12","12:23 PM","SATENA","NSE 2354","Cancúm",23,"Exit"));
        fligths.add(new Fligth("2019-05-07","09:13 AM","LANCO","L7 3647","London",17,"Exit"));
        fligths.add(new Fligth("2019-07-28","07:26 AM","AVIANCA","AVA 8553","Moscú",15,"Arrival"));
        fligths.add(new Fligth("2019-12-26","04:28 AM","TAMPA CARGO","QT 0235","París",21,"Exit"));
        fligths.add(new Fligth("2019-06-25","11:21 AM","WINGO","RPB 1253","London",9,"Arrival"));
        fligths.add(new Fligth("2019-03-12","03:07 AM","AVIANCA","AVA 4743","Chicago",6,"Exit"));
        fligths.add(new Fligth("2019-09-28","12:23 PM","SATENA","NSE 8794","Lima",11,"Arrival"));
        fligths.add(new Fligth("2019-09-16","08:14 AM","WINGO","RPB 2457","Roma",40,"Exit"));
        fligths.add(new Fligth("2019-07-09","06:23 AM","COPA AIRLINES","ARE 5467","Salem",4,"Arrival"));
        fligths.add(new Fligth("2019-11-24","06:54 AM","SATENA","NSE 4256","Bangladesh",3,"Exit"));
        fligths.add(new Fligth("2019-07-07","08:42 AM","LATAM","LA 8786","Madrid",12,"Arrival"));
        fligths.add(new Fligth("2019-06-30","10:08 AM","SATENA","NSE 6780","Cali",41,"Exit"));
        fligths.add(new Fligth("2019-09-16","12:55 PM","VIVA COLOMBIA","VPP 0535","Maimi",35,"Exit"));

        airport = new Airport();
        airport.setFligths(fligths);
    }

    //
    @Test
    public void testAirport1() {
        setupScenary1();
        assertNull(airport, "The airport reference is not null.");

    }

    //
    @Test
    public void testAirport2() {
        setupScenary2();

        assertNotNull(airport, "The reference to Airport was not initialized");
        assertEquals(0, airport.getTimeSearch(), "The time search is different.");
        assertNotNull(airport.getFligths(), "The list of fligths was not initialized");

    }

    @Test
    public void testLoadDates() {
        setupScenary2();
        String pDates = "test-data/test-dates.txt";
        List<String> dates = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pDates));
            String line = br.readLine();
            while (line != null) {
                dates.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        airport.loadDates(pDates);

        for (int i = 0; i < airport.getFligths().size(); i++) {
            if (!airport.getFligths().get(i).equals(dates.get(i))) {
                fail("The element of the list of dates is wrong");
            }
        }


    }

    //
    @Test
    public void testLoadTimes() {
        setupScenary2();
        String pTimes = "test-data/test-times.txt";
        List<String> times = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(pTimes)));
            String line = br.readLine();
            while (line != null) {
                times.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        airport.loadTimes(pTimes);

        for (int i = 0; i < airport.getFligths().size(); i++) {
            if (!airport.getFligths().get(i).equals(times.get(i))) {
                fail("The element of the list of dates is wrong");
            }
        }



    }

    //
    @Test
    public void testLoadAirlines() {
        setupScenary2();
        String pAirlines = "test-data/test-airlines.txt";
        List<String> airlines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(pAirlines)));
            String line = br.readLine();
            while (line != null) {
                airlines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        airport.loadTimes(pAirlines);

        for (int i = 0; i < airport.getFligths().size(); i++) {
            if (!airport.getFligths().get(i).equals(airlines.get(i))) {
                fail("The element of the list of dates is wrong");
            }
        }

    }

    //
    @Test
    public void testLoadCities() {
        setupScenary2();
        String pCities = "test-data/test-cities.txt";
        List<String> cities = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(pCities)));
            String line = br.readLine();
            while (line != null) {
                cities.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        airport.loadTimes(pCities);

        for (int i = 0; i < airport.getFligths().size(); i++) {
            if (!airport.getFligths().get(i).equals(cities.get(i))) {
                fail("The element of the list of dates is wrong");
            }
        }

    }

    //
    @Test
    public void testGenerateRandomFligth() {
        setupScenary2();

        int numberFligths = 20;
        try {
            airport.generateRandomFligths(numberFligths);
            assertTrue(airport.getFligths().size() == numberFligths, "The size of the list of fligths is differente");
        }catch (NegativeArraySizeException e){
            fail("The number of fligths is negative");
        }

        /**
        numberFligths = -20;
        try {
            airport.generateRandomFligths(numberFligths);
            assertTrue(airport.getFligths().size() == numberFligths, "The size of the list of fligths is differente");
        }catch (NegativeArraySizeException e){
            e.printStackTrace();
        }


    }


    @Test
    public void testSortByNaturalOrder() {
        setupScenary3();

        airport.sortByNaturalOrder();

        for (int i = 0; i < airport.getFligths().size()-1; i++) {
            if (airport.getFligths().get(i).getTime().compareTo(airport.getFligths().get(i+1).getTime())>=1){
                fail("The list of fligths was not sorted correctly for natural order.");
            }

        }



    }

    //
    @Test
    public void testSortByDate() {
        setupScenary3();

        airport.sortByDate();

        for (int i = 0; i < airport.getFligths().size()-1; i++) {
            if (airport.getFligths().get(i).getDate().compareTo(airport.getFligths().get(i+1).getDate())>=1){
                fail("The list of fligths was not sorted correctly for natural order.");
            }

        }
    }

    //
    @Test
    public void testSortByAirline() {
        setupScenary2();
    }

    //
    @Test
    public void testSortByFligth() {
        setupScenary2();
    }

    //
    @Test
    public void testSortByCity() {
        setupScenary2();
    }

    //
    @Test
    public void testSortByGate() {
        setupScenary2();
    }

    //
    @Test
    public void testSortByState() {
        setupScenary2();
    }

    //
    @Test
    public void testSearchBySequientialSearch() {
        setupScenary3();

        // Search for date
        Criteria criteria = Criteria.DATE;
        String valueSearch = "2019-09-28";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getDate(), valueSearch, "The flith searched for date not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.DATE;
        valueSearch = "2019-03-23";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getDate(),valueSearch, "The fligth searched for date exist");
        }catch (FlitgthNoExistException e){}

        // Search for time
        criteria = Criteria.TIME;
        valueSearch = "03:07 AM";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getTime(), valueSearch, "The flith searched for time not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.TIME;
        valueSearch = "11:11 AM";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getTime(),valueSearch,"The fligth searched for time exist");
        }catch (FlitgthNoExistException e){}

        // Searche for airline
        criteria = Criteria.AIRLINE;
        valueSearch = "AVIANCA";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getAirline(), valueSearch, "The flith searched for airline not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.AIRLINE;
        valueSearch = "EMIRATES AIRLINES";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getAirline(),valueSearch,"The fligth searched for airline exist");
        }catch (FlitgthNoExistException e){}

        // Search for  code fligth
        criteria = Criteria.FLIGTH;
        valueSearch = "AVA 4743";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getFligth(), valueSearch, "The fligth searched for code fligth not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.FLIGTH;
        valueSearch = "ARE 3232";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getFligth(),valueSearch,"The fligth searched for code fligth exist");
        }catch (FlitgthNoExistException e){}

        // Search for city
        criteria = Criteria.CITY;
        valueSearch = "London";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getCity(), valueSearch, "The fligth searched for city not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.CITY;
        valueSearch = "El Cairo";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getCity(),valueSearch,"The fligth searched for city exist");
        }catch (FlitgthNoExistException e){}

        // Search for gate
        criteria = Criteria.GATE;
        valueSearch = "15";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getGate(), 15, "The fligth searched for gate not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.GATE;
        valueSearch = "57";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getGate(),valueSearch,"The fligth searched for gate exist");
        }catch (FlitgthNoExistException e){}

        // Search for State
        criteria = Criteria.STATE;
        valueSearch = "Exit";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getState(), valueSearch, "The fligth searched for state not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.STATE;
        valueSearch = "Deleyed";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getState(),valueSearch,"The fligth searched for state exist");
        }catch (FlitgthNoExistException e){}


    }

    //
    @Test
    public void testSearchByBinarySearch() {
        setupScenary3();

        // Search for date
        Criteria criteria = Criteria.DATE;
        String valueSearch = "2019-09-16";
        try {
            assertEquals(airport.searchByBinarySearch(criteria,valueSearch).getDate(), valueSearch, "The flith searched for date not exist");
        }catch (FlitgthNoExistException e){
        }

        criteria = Criteria.DATE;
        valueSearch = "2019-01-23";
        try{
            assertNotEquals(airport.searchByBinarySearch(criteria,valueSearch).getDate(),valueSearch, "The fligth searched for date exist");
        }catch (FlitgthNoExistException e){}

        // Search for time
        criteria = Criteria.TIME;
        valueSearch = "03:07 AM";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getTime(), valueSearch, "The flith searched for time not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.TIME;
        valueSearch = "11:11 AM";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getTime(),valueSearch,"The fligth searched for time exist");
        }catch (FlitgthNoExistException e){}

        // Searche for airline
        criteria = Criteria.AIRLINE;
        valueSearch = "AVIANCA";
        try {
            assertEquals(airport.searchByBinarySearch(criteria, valueSearch).getAirline(), valueSearch, "The flith searched for airline not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.AIRLINE;
        valueSearch = "EMIRATES AIRLINES";
        try{
            assertNotEquals(airport.searchByBinarySearch(criteria,valueSearch).getAirline(),valueSearch,"The fligth searched for airline exist");
        }catch (FlitgthNoExistException e){}

        // Search for  code fligth
        criteria = Criteria.FLIGTH;
        valueSearch = "NSE 8794";
        try {
            assertEquals(airport.searchByBinarySearch(criteria, valueSearch).getFligth(), valueSearch, "The fligth searched for code fligth not exist");
        }catch (FlitgthNoExistException e){

        }

        criteria = Criteria.FLIGTH;
        valueSearch = "ARE 3232";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getFligth(),valueSearch,"The fligth searched for code fligth exist");
        }catch (FlitgthNoExistException e){}

        // Search for city
        criteria = Criteria.CITY;
        valueSearch = "London";
        try {
            assertEquals(airport.searchBySequentialSearch(criteria, valueSearch).getCity(), valueSearch, "The fligth searched for city not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.CITY;
        valueSearch = "El Cairo";
        try{
            assertNotEquals(airport.searchByBinarySearch(criteria,valueSearch).getCity(),valueSearch,"The fligth searched for city exist");
        }catch (FlitgthNoExistException e){}

        // Search for gate
        criteria = Criteria.GATE;
        valueSearch = "15";
        try {
            assertEquals(airport.searchByBinarySearch(criteria, valueSearch).getGate(), 15, "The fligth searched for gate not exist");
        }catch (FlitgthNoExistException e){
        }

        criteria = Criteria.GATE;
        valueSearch = "57";
        try{
            assertNotEquals(airport.searchBySequentialSearch(criteria,valueSearch).getGate(),valueSearch,"The fligth searched for gate exist");
        }catch (FlitgthNoExistException e){}

        // Search for State
        criteria = Criteria.STATE;
        valueSearch = "Exit";
        try {
            assertEquals(airport.searchByBinarySearch(criteria, valueSearch).getState(), valueSearch, "The fligth searched for state not exist");
        }catch (FlitgthNoExistException e){
            e.printStackTrace();
        }

        criteria = Criteria.STATE;
        valueSearch = "Deleyed";
        try{
            assertNotEquals(airport.searchByBinarySearch(criteria,valueSearch).getState(),valueSearch,"The fligth searched for state exist");
        }catch (FlitgthNoExistException e){}

    }

*/
}
