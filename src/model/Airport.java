package model;

import exceptions.FlitgthNoExistException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private Random generatorRandom;
    private Fligth firstFligth;
    private int numberFligths;

    // List of atributes
    private List<String> dates;
    private List<String> times;
    private List<String> airlines;
    private List<String> cities;

    // Constructor
    public Airport(){
       // timeSearch = 0;
        criteria = null;
        generatorRandom = new Random();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        airlines = new ArrayList<>();
        cities = new ArrayList<>();

        firstFligth = null;
        numberFligths = 0;
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

    public void generateRandomFligths(int nFligths) throws NegativeArraySizeException {

        if (nFligths <= 0) {
            throw new NegativeArraySizeException();
        } else {
            loadDates(DATES_PATH);
            loadTimes(TIMES_PATH);
            loadAirlines(AIRLINES_PATH);
            loadCities(CITIES_PATH);
            int index = 0;
            while ( numberFligths < nFligths) {
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
                // Create and add a new fligth to the list.
                Fligth newFligth = new Fligth(date, time, airline, fligth, city, gate, state);
                addFligth(newFligth);
            }
           // sortByNaturalOrder();
        }
    }

    // Add the Fligth in the final
    public void addFligth(Fligth newFligth){
        if(firstFligth == null){
            firstFligth = newFligth;
        }else{
            Fligth aux = firstFligth;
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newFligth);
        }
        numberFligths++;
    }


    public int[] createPage(int pageIndex){
        int fromIndex = pageIndex*ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex+ROWS_PER_PAGE,numberFligths);
        int[] indexs = {fromIndex,toIndex};
        return indexs;
    }


    public void emptyList(){
        numberFligths = 0;
        firstFligth = null;
    }




    // With Comparable
    public void sortByNaturalOrder(){
        long star,end;
        star = System.currentTimeMillis();

        Fligth aux1, aux2, aux3, aux4, aux5;
        aux1 = aux2 = firstFligth;
        while (aux2.getNext() != null) {
            aux3 = aux4 = aux2.getNext();
            while (aux4 != null) {
                if (aux2.compareTo(aux4) > 0) {
                    if (aux2.getNext() == aux4) {
                        if (aux2 == firstFligth) {
                            aux2.setNext(aux4.getNext());
                            aux4.setNext(aux2);
                            aux5 = aux2;
                            aux2 = aux4;
                            aux4 = aux5;
                            aux3 = aux4;
                            firstFligth = aux2;
                            aux4 = aux4.getNext();
                        }
                        else {
                            aux2.setNext(aux4.getNext());
                            aux4.setNext(aux2);
                            aux1.setNext(aux4);
                            aux5 = aux2;
                            aux2 = aux4;
                            aux4 = aux5;
                            aux3 = aux4;
                            aux4 = aux4.getNext();
                        }
                    }
                    else {
                        if (aux2 == firstFligth) {
                            aux5 = aux2.getNext();
                            aux2.setNext(aux4.getNext());
                            aux4.setNext(aux5);
                            aux3.setNext(aux2);
                            aux5 = aux2;
                            aux2 = aux4;
                            aux4 = aux5;
                            aux3 = aux4;
                            aux4 = aux4.getNext();
                            firstFligth = aux2;
                        }
                        else {
                            aux5 = aux2.getNext();
                            aux2.setNext(aux4.getNext());
                            aux4.setNext(aux5);
                            aux3.setNext(aux2);
                            aux1.setNext(aux4);
                            aux5 = aux2;
                            aux2 = aux4;
                            aux4 = aux5;
                            aux3 = aux4;
                            aux4 = aux4.getNext();
                        }
                    }
                }
                else {
                    aux3 = aux4;
                    aux4 = aux4.getNext();
                }
            }
            aux1 = aux2;
            aux2 = aux2.getNext();
        }


        end = System.currentTimeMillis();
        timeSearch = (end-star)/1000;
    }



    // With Bubble
    public void sortByDate(){
        long star,end;
        star = System.currentTimeMillis();

        for (int i = 0; i < numberFligths; i++) {
            Fligth current = firstFligth;
            Fligth prev = null;
            Fligth next = firstFligth.getNext();
            while(next != null){
                if(current.getDate().compareTo(next.getDate())>0){
                    if (prev != null){
                        Fligth auxNext = next.getNext();
                        prev.setNext(next);
                        next.setNext(current);
                        current.setNext(auxNext);
                    } else {
                        Fligth auxNext = next.getNext();
                        firstFligth = next;
                        next.setNext(current);
                        current.setNext(auxNext);
                    }
                    prev = next;
                    next = current.getNext();
                } else {
                    prev = current;
                    current = next;
                    next = next.getNext();
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

            Fligth aux1, aux2, aux3, aux4, aux5;
            aux1 = aux2 = firstFligth;
            while (aux2.getNext() != null) {
                aux3 = aux4 = aux2.getNext();
                while (aux4 != null) {
                    if (aux2.getAirline().compareToIgnoreCase(aux4.getAirline()) > 0) {
                        if (aux2.getNext() == aux4) {
                            if (aux2 == firstFligth) {
                                aux2.setNext(aux4.getNext());
                                aux4.setNext(aux2);
                                aux5 = aux2;
                                aux2 = aux4;
                                aux4 = aux5;
                                aux3 = aux4;
                                firstFligth = aux2;
                                aux4 = aux4.getNext();
                            }
                            else {
                                aux2.setNext(aux4.getNext());
                                aux4.setNext(aux2);
                                aux1.setNext(aux4);
                                aux5 = aux2;
                                aux2 = aux4;
                                aux4 = aux5;
                                aux3 = aux4;
                                aux4 = aux4.getNext();
                            }
                        }
                        else {
                            if (aux2 == firstFligth) {
                                aux5 = aux2.getNext();
                                aux2.setNext(aux4.getNext());
                                aux4.setNext(aux5);
                                aux3.setNext(aux2);
                                aux5 = aux2;
                                aux2 = aux4;
                                aux4 = aux5;
                                aux3 = aux4;
                                aux4 = aux4.getNext();
                                firstFligth = aux2;
                            }
                            else {
                                aux5 = aux2.getNext();
                                aux2.setNext(aux4.getNext());
                                aux4.setNext(aux5);
                                aux3.setNext(aux2);
                                aux1.setNext(aux4);
                                aux5 = aux2;
                                aux2 = aux4;
                                aux4 = aux5;
                                aux3 = aux4;
                                aux4 = aux4.getNext();
                            }
                        }
                    }
                    else {
                        aux3 = aux4;
                        aux4 = aux4.getNext();
                    }
                }
                aux1 = aux2;
                aux2 = aux2.getNext();
            }

        end = System.currentTimeMillis();
        timeSearch = (end-star)/1000;
    }

    // With
    public void sortByFligth(){
        long star,end;
        star = System.currentTimeMillis();

        if(firstFligth.getNext() != null){
            Fligth p =firstFligth;
            Fligth current = firstFligth.getNext();
            Fligth prev = firstFligth;
            while (current != null){
                if(prev.getFligth().compareTo(current.getFligth()) <= 0){
                    current = current.getNext();
                    prev = prev.getNext();
                }else {
                    if (firstFligth.getFligth().compareTo(current.getFligth()) > 0){
                        prev.setNext(current.getNext());
                        current.setNext(firstFligth);
                        firstFligth = current;
                    }else {
                        p = firstFligth;
                        while (p.getNext() != null && p.getNext().getFligth().compareTo(current.getFligth()) < 0){
                            p = p.getNext();
                        }
                        prev.setNext(current.getNext());
                        current.setNext(p.getNext());
                        p.setNext(current);
                    }
                }
                current = current.getNext();
            }
        }


        end = System.currentTimeMillis();
        timeSearch = (end-star)/1000;
    }

    // With insertion
    public void sortByCity() {

        long star, end;
        star = System.currentTimeMillis();

        if(firstFligth.getNext() != null){
            Fligth p =firstFligth;
            Fligth current = firstFligth.getNext();
            Fligth prev = firstFligth;
            while (current != null){
                if(prev.getCity().compareTo(current.getCity()) <= 0){
                    current = current.getNext();
                    prev = prev.getNext();
                }else {
                    if (firstFligth.getCity().compareTo(current.getCity()) > 0){
                        prev.setNext(current.getNext());
                        current.setNext(firstFligth);
                        firstFligth = current;
                    }else {
                        p = firstFligth;
                        while (p.getNext() != null && p.getNext().getCity().compareTo(current.getCity()) < 0){
                            p = p.getNext();
                        }
                        prev.setNext(current.getNext());
                        current.setNext(p.getNext());
                        p.setNext(current);
                    }
                }
                current = current.getNext();
            }
        }


        end = System.currentTimeMillis();
        timeSearch = (end - star) / 1000;
    }

    // With bubble
    public void sortByGate(){
        long star = 0, end = 0;
        star = System.currentTimeMillis();

        for (int i = 0; i < numberFligths; i++) {
            Fligth current = firstFligth;
            Fligth prev = null;
            Fligth next = firstFligth.getNext();
            while(next != null){
                if(current.getGate() > next.getGate()){
                    if (prev != null){
                        Fligth auxNext = next.getNext();
                        prev.setNext(next);
                        next.setNext(current);
                        current.setNext(auxNext);
                    } else {
                        Fligth auxNext = next.getNext();
                        firstFligth = next;
                        next.setNext(current);
                        current.setNext(auxNext);
                    }
                    prev = next;
                    next = current.getNext();
                } else {
                    prev = current;
                    current = next;
                    next = next.getNext();
                }
            }
        }


        end = System.currentTimeMillis();
        timeSearch = (end-star)/1000;
    }

    // With
    public void sortByState(){
        long star,end;
        star = System.currentTimeMillis();

        if(firstFligth.getNext() != null){
            Fligth p =firstFligth;
            Fligth current = firstFligth.getNext();
            Fligth prev = firstFligth;
            while (current != null){
                if(prev.getState().compareTo(current.getState()) <= 0){
                    current = current.getNext();
                    prev = prev.getNext();
                }else {
                    if (firstFligth.getState().compareTo(current.getState()) > 0){
                        prev.setNext(current.getNext());
                        current.setNext(firstFligth);
                        firstFligth = current;
                    }else {
                        p = firstFligth;
                        while (p.getNext() != null && p.getNext().getState().compareTo(current.getState()) < 0){
                            p = p.getNext();
                        }
                        prev.setNext(current.getNext());
                        current.setNext(p.getNext());
                        p.setNext(current);
                    }
                }
                current = current.getNext();
            }
        }


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
                searched = ssGate(Integer.parseInt(valueSearch));
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
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getDate().equalsIgnoreCase(value)) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    private Fligth ssTime(String value){
        Fligth s = null;
        boolean found = false;
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getTime().equalsIgnoreCase(value)) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    private Fligth ssAirline(String value){
        Fligth s = null;
        boolean found = false;
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getCity().equalsIgnoreCase(value)) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    private Fligth ssFligth(String value){
        Fligth s = null;
        boolean found = false;
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getFligth().equalsIgnoreCase(value)) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    private Fligth ssCity(String value){
        Fligth s = null;
        boolean found = false;
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getCity().equalsIgnoreCase(value)) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    private Fligth ssGate(int value){
        Fligth s = null;
        boolean found = false;
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getGate() == value) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    private Fligth ssState(String value){
        Fligth s = null;
        boolean found = false;
        if(firstFligth != null) {
            Fligth current = firstFligth;
            while (current != null && !found) {
                if (current.getState().equalsIgnoreCase(value)) {
                    s = current;
                    found = true;
                }
                current = current.getNext();
            }
        }
        return s;
    }

    public Fligth getFirstFligth() {
        return firstFligth;
    }

    public void setFirstFligth(Fligth firstFligth) {
        this.firstFligth = firstFligth;
    }

    public int getNumberFligths() {
        return numberFligths;
    }

    public void setNumberFligths(int numberFligths) {
        this.numberFligths = numberFligths;
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

}
