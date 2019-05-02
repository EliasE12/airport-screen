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
    public final static String DATES_PATH = "data/dates.txt";
    public final static String TIMES_PATH = "data/times.txt";
    public final static String AIRLINES_PATH = "data/airlines.txt";
    public final static String CITIES_PATH = "data/cities.txt";
    public final static int ROWS_PER_PAGE = 20;

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
    private List<String> cities;
    private List<String> states;

    // Constructor
    public Airport(){
       // timeSearch = 0;
        criteria = null;
        generatorRandom = new Random();
        fligths = new ArrayList<>();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        airlines = new ArrayList<>();
        cities = new ArrayList<>();
        states = new ArrayList<>();

    }

    // Methods
    public void loadDates(String pathDates){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathDates));
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

    public void loadTimes(String pathTimes){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathTimes));
            String line = br.readLine();
            while (line != null) {
                if(line.length()<8){
                    line = "0"+line;
                }
                times.add(line);
                line = br.readLine();
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadAirlines(String pathAirlines){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathAirlines));
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

    public void loadCities(String pathCities){
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathCities));
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

    public void generateRandomFligths(int numberFligths) throws NegativeArraySizeException {

        if (numberFligths <= 0) {
            throw new NegativeArraySizeException();
        } else {
            loadDates(DATES_PATH);
            loadTimes(TIMES_PATH);
            loadAirlines(AIRLINES_PATH);
            loadCities(CITIES_PATH);
            int index = 0;
            while (fligths.size() < numberFligths) {
                // Generete the random atributes
                index =  generatorRandom.nextInt(dates.size());
                String date = dates.get((index));
                index =  generatorRandom.nextInt(times.size());
                String time = times.get(index);
                index =  generatorRandom.nextInt(airlines.size());
                String airline = airlines.get(index);
                String fligth = null;
                index =  generatorRandom.nextInt(cities.size());
                String city = cities.get(index);
                int gate =  generatorRandom.nextInt(45) + 1;
                index = (int) (Math.random()*2)+1;
                String state;
                if(index==1){
                    state = "Exit";
                }else{
                    state = "Arrival";
                }
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
                // Create and add a new fligth to de list.
                Fligth newFligth = new Fligth(date, time, airline, fligth, city, gate, state);
                fligths.add(newFligth);
            }
            sortByNaturalOrder();
        }
    }

    public int[] createPage(int pageIndex){
        int fromIndex = pageIndex*ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex+ROWS_PER_PAGE,fligths.size());
        int[] indexs = {fromIndex,toIndex};
        return indexs;
    }

    // With Comparable
    public void sortByNaturalOrder(){
        long star,end;
        star = System.currentTimeMillis();
        Collections.sort(fligths);
        end = System.currentTimeMillis();
        timeSearch = (end-star)/1000;
    }

    // With Bubble
    public void sortByDate(){
        long star,end;
        star = System.currentTimeMillis();
        Fligth aux;
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
        timeSearch = (end-star)/1000;
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
        timeSearch = (end-star)/1000;
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
        timeSearch = (end-star)/1000;
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
        timeSearch = (end-star)/1000;
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
        timeSearch = (end-star)/1000;
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
        timeSearch = (end-star)/1000;
    }

    // Sequential Search
    public Fligth searchBySequentialSearch(Criteria criter, String valueSearch) throws FlitgthNoExistException {
        Fligth searched = null;
        long start,end;
        start = System.currentTimeMillis();
        switch (criter){
            case DATE:
                searched = ssDate(valueSearch);
            break;
            case TIME:
                searched = ssTime(valueSearch);
            break;
            case AIRLINE:
                searched = ssAirline(valueSearch);
            break;
            case FLIGTH:
                searched = ssFligth(valueSearch);
            break;
            case CITY:
                searched = ssCity(valueSearch);
            break;
            case GATE:
                searched = ssGate(valueSearch);
            break;
            case STATE:
                searched = ssState(valueSearch);
            break;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start)/1000;

        if(searched == null){
            throw new FlitgthNoExistException();
        }else {
            return searched;
        }
    }

    private Fligth ssDate(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getDate().equalsIgnoreCase(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth ssTime(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getTime().equalsIgnoreCase(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth ssAirline(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getAirline().equalsIgnoreCase(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth ssFligth(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getFligth().equalsIgnoreCase(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth ssCity(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getCity().equalsIgnoreCase(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    private Fligth ssGate(String value){
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

    private Fligth ssState(String value){
        Fligth s = null;
        boolean found = false;
        int i = 0;
        while (i<fligths.size() && !found){
            if(fligths.get(i).getState().equalsIgnoreCase(value)){
                s = fligths.get(i);
                found = true;
            }
            i++;
        }
        return s;
    }

    // Binary Search
    public Fligth searchByBinarySearch(Criteria criteria, String valueSearch) throws FlitgthNoExistException {
        Fligth searched = null;
        long start,end;
        start = System.currentTimeMillis();
        switch (criteria){
            case DATE:
                searched = bsDate(valueSearch);
            break;
            case TIME:
                searched = bsTime(valueSearch);
            break;
            case AIRLINE:
                searched = bsAirline(valueSearch);
            break;
            case FLIGTH:
                searched = bsFligth(valueSearch);
            break;
            case CITY:
                searched = bsCity(valueSearch);
            break;
            case GATE:
                searched = bsGate(valueSearch);
            break;
            case STATE:
                searched = bsState(valueSearch);
            break;
        }
        end = System.currentTimeMillis();
        timeSearch = (end-start)/1000;

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
            if (fligths.get(mid).getDate().equalsIgnoreCase(value)){
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
            if (fligths.get(mid).getTime().equalsIgnoreCase(value)){
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
            if (fligths.get(mid).getAirline().equalsIgnoreCase(value)){
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
            if (fligths.get(mid).getFligth().equalsIgnoreCase(value)){
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
            if (fligths.get(mid).getCity().equalsIgnoreCase(value)){
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
            if (fligths.get(mid).getState().equalsIgnoreCase(value)){
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
