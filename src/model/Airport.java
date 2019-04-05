package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Airport {

    public static final String PATH_DATA = "data/AirportScreenData.txt";
    public enum Criteria {DATE,DEPARTURE_TIME,AIRLINE,CODE_FLIGTH,CITY,GATE,STATE};

    private long timeSearch;
    private Criteria criteria;
    private List<Fligth> fligths;

    private List<Fligth> auxFligths;

    private List<String> dates;
    private List<String> times;
    private List<String> airlines;
    private List<String> codeFligths;
    private List<String> cities;
    private List<Integer> gates;
    private List<String> states;

    public Airport(){
        auxFligths = new ArrayList<Fligth>();
        timeSearch = 0;
        criteria = null;
        fligths = new ArrayList<Fligth>();

        dates = new ArrayList<String>();
        times = new ArrayList<String>();
        airlines = new ArrayList<String>();
        codeFligths = new ArrayList<String>();
        cities = new ArrayList<String>();
        gates = new ArrayList<Integer>();
        states = new ArrayList<String>();

    }


    public void loadData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH_DATA));
            String line = br.readLine();
            while (line != null) {
                    String[] parts = line.split(";");
                    String date = parts[0];
                    String dTime = parts[1];
                    String nAirline = parts[2];
                    String cFligth = parts[3];
                    String city = parts[4];
                    int gate = Integer.parseInt(parts[5]);
                    String state = parts[6];
                    Fligth newFligth = new Fligth(date, dTime, nAirline, cFligth, city, gate, state);
                    auxFligths.add(newFligth);
                line = br.readLine();
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadDates(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/dates.txt"));
            String line = br.readLine();
            while (line != null) {
                dates.add(line);
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadTimes(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/times.txt"));
            String line = br.readLine();
            while (line != null) {
                times.add(line);
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadAirlines(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/airlines.txt"));
            String line = br.readLine();
            while (line != null) {
                airlines.add(line);
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadCodeFligths(){}

    public void loadCities(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/cities.txt"));
            String line = br.readLine();
            while (line != null) {
                cities.add(line);
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void generateGates(){}

    public void loadStates(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/states.txt"));
            String line = br.readLine();
            while (line != null) {
                states.add(line);
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }



    public void genereteRandomFligths(int numberFligths){
        while(fligths.size() <= numberFligths){
            String date = dates.get((int) Math.random()*dates.size()+1);
            String time = times.get((int) Math.random()*times.size()+1);
            String airline = airlines.get((int) Math.random()*airlines.size()+1);
            
            Fligth otherNew = new Fligth(,,,,,,);
            fligths.add(otherNew);
        }
    }

    public ObservableList<Fligth> observableFligths(){
        ObservableList<Fligth> list = FXCollections.observableArrayList();
        for (Fligth current: fligths) {
            list.add(current);
        }
        return list;
    }

    public void nextPage(){}

    public void backPage(){}

    // With Comparable
    public void sortByNaturalOrder(){
        Collections.sort(fligths);
    }

    // With Bubble
    public void sortByDate(){

        Fligth aux = null;
        for (int i = 0; i<fligths.size()-1; i++){
            for (int j = 0; j<fligths.size()-i-1; j++){
                if (fligths.get(j).getDate().compareTo(fligths.get(j+1).getDate())>0){
                    aux = fligths.get(j);
                    fligths.set(j,fligths.get(j+1));
                    fligths.set(j+1,aux);
                }
            }
        }
    }

    // With Selection
    public void sortByAirline(){

        for (int i = 0; i < fligths.size()-1; i++) {
            int min = i;
            for (int j = i + 1; j < fligths.size(); j++) {
                if (fligths.get(j).getAirline().getName().compareTo(fligths.get(min).getAirline().getName()) < 0) {
                    min = j;
                }
            }
            Fligth aux = fligths.get(i);
            fligths.set(i,fligths.get(min));
            fligths.set(min,aux);
        }
    }

    public void sortByCodeFligth(){

        Collections.sort(fligths, new Comparator<Fligth>() {
            @Override
            public int compare(Fligth fligth1, Fligth fligth2) {
                int comparations;
                if (fligth1.getCodeFligth().compareTo(fligth2.getCodeFligth()) > 0)
                    comparations = 1;
                else if (fligth1.getCodeFligth().compareTo(fligth2.getCodeFligth()) < 0)
                    comparations = -1;
                else
                    comparations = 0;

                return comparations;
            }
        });
    }

    // With insertion
    public void sortByCity(){

        for (int i = 1; i < fligths.size(); i++) {
            Fligth current = fligths.get(i);
            int j = i;
            while (j > 0 && fligths.get(j-1).getCity().compareTo(current.getCity()) > 0) {
                fligths.set(j, fligths.get(j-1));
                j--;
            }
            fligths.set(j,current);
        }
    }

    public void sortByGate(){
        Collections.sort(fligths, new Comparator<Fligth>() {
            @Override
            public int compare(Fligth fligth1, Fligth fligth2) {
                return (fligth1.getGate() - fligth2.getGate());
            }
        });
    }

    public void sortByState(){}


    public Fligth searchBySequentialSearch(String criteria, String valueSearch){
        Fligth searched = null;
        boolean found = false;
        int i = 0;
        long start,end;
        switch (criteria){
            case "DATE":
                start = System.currentTimeMillis();
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getDate().equals(valueSearch)){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
            case "DEPARTURE_TIME":
                start = System.currentTimeMillis();
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getTime().equals(valueSearch)){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
            case "AIRLINE":
                start = System.currentTimeMillis();
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getAirline().getName().equals(valueSearch)){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
            case "CODE_FLIGTH":
                start = System.currentTimeMillis();
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getCodeFligth().equals(valueSearch)){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
            case "CITY":
                start = System.currentTimeMillis();
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getCity().equals(valueSearch)){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
            case "GATE":
                start = System.currentTimeMillis();
                int gate = Integer.parseInt(valueSearch);
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getGate() == gate){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
            case "STATE":
                start = System.currentTimeMillis();
                while (i<fligths.size() && !found){
                    if(fligths.get(i).getState().equals(valueSearch)){
                        searched = fligths.get(i);
                        found = true;
                    }
                }
                end = System.currentTimeMillis();
                timeSearch = (end-start);
            break;
        }
        return searched;
    }


    public Fligth searchByBinarySearch(String criteria, String valueSearch){
        return null;
    }

    public long calculateTimeSearch(){
        return 0;
    }



    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public List<Fligth> getAuxFligths() {
        return auxFligths;
    }

    public void setAuxFligths(List<Fligth> auxFligths) {
        this.auxFligths = auxFligths;
    }

    public long getTimeSearch() {
        return timeSearch;
    }

    public void setTimeSearch(long timeSearch) {
        this.timeSearch = timeSearch;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<Fligth> getFligths() {
        return fligths;
    }

    public void setFligths(List<Fligth> fligths) {
        this.fligths = fligths;
    }
}
