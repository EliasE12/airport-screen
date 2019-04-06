package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Class
public class Airport {
    // Constants
    public static final String PATH_DATA = "data/AirportScreenData.txt";
    public enum Criteria {DATE,TIME,AIRLINE,FLIGTH,CITY,GATE,STATE};
    // Atributes
    private long timeSearch;
    private Criteria criteria;
    private List<Fligth> fligths;
    // List of atributes
    private List<String> dates;
    private List<String> times;
    private List<String> airlines;
    private List<String> codeFligths;
    private List<String> cities;
    private List<Integer> gates;
    private List<String> states;

    // Constructor
    public Airport(){
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

    // Methods
    public void loadDates(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/dates.txt"));
            String line = br.readLine();
            while (line != null) {
                dates.add(line);
                line = br.readLine();
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
                line = br.readLine();
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
                line = br.readLine();
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadCities(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/cities.txt"));
            String line = br.readLine();
            while (line != null) {
                cities.add(line);
                line = br.readLine();
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadStates(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/states.txt"));
            String line = br.readLine();
            while (line != null) {
                states.add(line);
                line = br.readLine();
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void genereteRandomFligths(int numberFligths) {

        loadDates(); loadTimes(); loadAirlines(); loadCities(); loadStates();

        while (fligths.size() <= numberFligths) {
            // Generete the random atributes
            String date = dates.get((int) Math.random() * dates.size() + 1);
            String time = times.get((int) Math.random() * times.size() + 1);
            String airline = airlines.get((int) Math.random() * airlines.size() + 1);
            String fligth = null;
            String city = cities.get((int) Math.random() * cities.size() + 1);
            int gate = (int) Math.random() * 25 + 1;
            String state = states.get((int) Math.random() * states.size() + 1);
            // Create the code fligth
            int numbCode = (int) Math.floor(Math.random() * (1000 - (9000 + 1)) + (9000));
            switch (airline) {
                case "AVIANCA":
                    fligth = "AVA " + numbCode;
                    break;
                case "LATAM":
                    fligth = "LA " + numbCode;
                    break;
                case "COPA AIRLINES":
                    fligth = "ARE " + numbCode;
                    break;
                case "COLOMBIA":
                    fligth = "RPB " + numbCode;
                    break;
                case "VIVA COLOMBIA":
                    fligth = "VVP " + numbCode;
                    break;
                case "WINGO":
                    fligth = "RPB " + numbCode;
                    break;
                case "SATENA":
                    fligth = "NSE " + numbCode;
                    break;
                case "LAN COLOMBIA":
                    fligth = "4C " + numbCode;
                    break;
                case "LANCO":
                    fligth = "L7 " + numbCode;
                    break;
                case "TAMPA CARGO":
                    fligth = "QT " + numbCode;
                    break;
            }

            // Create and add a new fligth to de list
            Fligth otherNew = new Fligth(date, time, airline, fligth, city, gate, state);
            fligths.add(otherNew);
        }
    }

    public ObservableList<Fligth> observableFligths(){
        ObservableList<Fligth> observableListFligths = FXCollections.observableArrayList(fligths);
        return observableListFligths;
    }

    public void nextPage(){}

    public void backPage(){}

    // With Comparable
    public void sortByNaturalOrder(){
        Collections.sort(fligths);
    }

    // With Bubble
    public void sortByTime(){
        Fligth aux = null;
        for (int i = 0; i<fligths.size()-1; i++){
            for (int j = 0; j<fligths.size()-i-1; j++){
                if (fligths.get(j).compareToTime(fligths.get(j+1))>0){
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
                if (fligths.get(j).getAirline().compareTo(fligths.get(min).getAirline()) < 0) {
                    min = j;
                }
            }
            Fligth aux = fligths.get(i);
            fligths.set(i,fligths.get(min));
            fligths.set(min,aux);
        }
    }

    public void sortByFligth(){
        Collections.sort(fligths, new Comparator<Fligth>() {
            @Override
            public int compare(Fligth fligth1, Fligth fligth2) {
                int comparation;
                if (fligth1.getFligth().compareTo(fligth2.getFligth()) > 0)
                    comparation = 1;
                else if (fligth1.getFligth().compareTo(fligth2.getFligth()) < 0)
                    comparation = -1;
                else
                    comparation = 0;
                return comparation;
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
            public int compare(Fligth o1, Fligth o2) {
                return (o1.getGate()-o2.getGate());
            }
        });
    }

    public void sortByState(){
        Collections.sort(fligths, new Comparator<Fligth>() {
            @Override
            public int compare(Fligth fligth1, Fligth fligth2) {
                int comparation;
                if (fligth1.getState().compareTo(fligth2.getState())>0)
                    comparation = 1;
                else if (fligth1.getState().compareTo(fligth2.getState())<0)
                    comparation = -1;
                else
                    comparation = 0;
                return comparation;
            }
        });
    }

    public Fligth searchBySequentialSearch(String criteria, String valueSearch){
        Fligth searched = null;
        switch (criteria){
            case "DATE":
                searched = lsDate(valueSearch);
            break;
            case "TIME":
                searched = lsTime(valueSearch);
            break;
            case "AIRLINE":
                searched = lsAirline(valueSearch);
            break;
            case "FLIGTH":
                searched = lsFligth(valueSearch);
            break;
            case "CITY":
                searched = lsCity(valueSearch);
            break;
            case "GATE":
                searched = lsGate(valueSearch);
            break;
            case "STATE":
                searched = lsState(valueSearch);
            break;
        }
        return searched;
    }

    private Fligth lsDate(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getDate().equals(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth lsTime(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getTime().equals(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth lsAirline(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getAirline().equals(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth lsFligth(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getFligth().equals(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth lsCity(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getCity().equals(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth lsGate(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getGate() == Integer.parseInt(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth lsState(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int i = 0;
        start = System.currentTimeMillis();
        while (i<fligths.size() && !found){
            if(fligths.get(i).getState().equals(value)){
                s = fligths.get(i);
                found = true;
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }


    public Fligth searchByBinarySearch(String criteria, String valueSearch){
        Fligth searched = null;
        switch (criteria){
            case "Date":
                searched = bsDate(valueSearch);
            break;
            case "TIME":
                searched = bsTime(valueSearch);
            break;
            case "AIRLINE":
                searched = bsAirline(valueSearch);
            break;
            case "FLIGTH":
                searched = bsFligth(valueSearch);
            break;
            case "CITY":
                searched = bsCity(valueSearch);
            break;
            case "GATE":
                searched = bsGate(valueSearch);
            break;
            case "STATE":
                searched = bsState(valueSearch);
            break;
        }
        return searched;
    }

    private Fligth bsDate(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getDate().equals(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getDate().compareTo(value)>0){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth bsTime(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getTime().equals(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getTime().compareTo(value)>0){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth bsAirline(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getAirline().equals(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getAirline().compareTo(value)>0){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth bsFligth(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getFligth().equals(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getTime().compareTo(value)>0){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth bsCity(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getCity().equals(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getCity().compareTo(value)>0){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth bsGate(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getGate() == Integer.parseInt(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getGate() > Integer.parseInt(value)){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
    }

    private Fligth bsState(String value){
        long start,end;
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
        start = System.currentTimeMillis();
        while(low<=high && !found){
            int mid = (low+high)/2;
            if (fligths.get(mid).getState().equals(value)){
                s = fligths.get(mid);
                found = true;
            }else if (fligths.get(mid).getState().compareTo(value)>0){
                high = mid-1;
            }else
                low = mid+1;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start);
        return s;
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
