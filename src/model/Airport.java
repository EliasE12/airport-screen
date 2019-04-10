package model;

import exceptions.FlitgthNoExistException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Class
public class Airport {
    // Constants
    public enum Criteria {DATE,TIME,AIRLINE,FLIGTH,CITY,GATE,STATE};
    // Atributes
    private long timeSearch;
    private Criteria criteria;
    private List<Fligth> fligths;
    private Random generatorRandom;
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
        //timeSearch = 0;
        criteria = null;
        generatorRandom = new Random();
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

    public void genereteRandomFligths(int numberFligths) throws NegativeArraySizeException {

        if (numberFligths <= 0) {
            throw new NegativeArraySizeException();
        } else {
            loadDates();
            loadTimes();
            loadAirlines();
            loadCities();
            loadStates();
            int index = 0;
            while (fligths.size() < numberFligths) {
                // Generete the random atributes
                index = (int) generatorRandom.nextInt(dates.size());
                String date = dates.get((index));
                index = (int) generatorRandom.nextInt(times.size());
                String time = times.get(index);
                index = (int) generatorRandom.nextInt(airlines.size());
                String airline = airlines.get(index);
                String fligth = null;
                index = (int) generatorRandom.nextInt(cities.size());
                String city = cities.get(index);
                int gate = (int) generatorRandom.nextInt(25) + 1;
                index = (int) generatorRandom.nextInt(states.size());
                String state = states.get(index);
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
            sortByNaturalOrder();
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
        long star,end;
        star = System.currentTimeMillis();
        Collections.sort(fligths);
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // With Bubble
    public void sortByDate(){
        long star,end;
        star = System.currentTimeMillis();
        Fligth aux = null;
        for (int i = 0; i<fligths.size(); i++){
            for (int j = 0; j<fligths.size()-i-1; j++){
                if (fligths.get(j).compareToDate(fligths.get(j+1))>0){
                    aux = fligths.get(j);
                    fligths.set(j,fligths.get(j+1));
                    fligths.set(j+1,aux);
                }
            }
        }
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // With Selection
    public void sortByAirline(){
        long star,end;
        star = System.currentTimeMillis();
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
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // With Comparator
    public void sortByFligth(){
        long star,end;
        star = System.currentTimeMillis();
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
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // With insertion
    public void sortByCity(){
        long star,end;
        star = System.currentTimeMillis();
        for (int i = 1; i < fligths.size(); i++) {
            Fligth current = fligths.get(i);
            int j = i;
            while (j > 0 && fligths.get(j-1).getCity().compareTo(current.getCity()) > 0) {
                fligths.set(j, fligths.get(j-1));
                j--;
            }
            fligths.set(j,current);
        }
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // With comparator
    public void sortByGate(){
        long star = 0, end = 0;
        star = System.currentTimeMillis();
        Collections.sort(fligths, new Comparator<Fligth>() {
            @Override
            public int compare(Fligth o1, Fligth o2) {
                return (o1.getGate()-o2.getGate());
            }
        });
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // With Comparator
    public void sortByState(){
        long star,end;
        star = System.currentTimeMillis();
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
        end = System.currentTimeMillis();
        timeSearch = (end-star);
    }

    // Sequential Search
    public Fligth searchBySequentialSearch(String criter, String valueSearch) throws FlitgthNoExistException {
        Fligth searched = null;
        long start,end;
        start = System.currentTimeMillis();
        switch (criter){
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
        end = System.currentTimeMillis();
        timeSearch = (end-start);

        if(searched == null){
            throw new FlitgthNoExistException();
        }else {
            return searched;
        }
    }

    private Fligth lsDate(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getDate().equals(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth lsTime(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getTime().equals(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth lsAirline(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getAirline().equals(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth lsFligth(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getFligth().equals(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth lsCity(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getCity().equals(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth lsGate(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getGate() == Integer.parseInt(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth lsState(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getState().equals(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    // Binary Search
    public Fligth searchByBinarySearch(String criteria, String valueSearch) throws FlitgthNoExistException {
        Fligth searched = null;
        long start,end;
        start = System.currentTimeMillis();
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
        end = System.currentTimeMillis();
        timeSearch = (end-start);

        if(searched == null){
            throw new FlitgthNoExistException();
        }else {
            return searched;
        }
    }

    private Fligth bsDate(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
        return s;
    }

    private Fligth bsTime(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
        return s;
    }

    private Fligth bsAirline(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
        return s;
    }

    private Fligth bsFligth(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
        return s;
    }

    private Fligth bsCity(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
        return s;
    }

    private Fligth bsGate(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
        return s;
    }

    private Fligth bsState(String value){
        Fligth s = null;
        boolean found = false;
        int low = 0;
        int high = fligths.size()-1;
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
