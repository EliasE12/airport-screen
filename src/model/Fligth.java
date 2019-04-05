package model;

import java.util.Comparator;

// Class
public class Fligth implements Comparable<Fligth>, Comparator<Fligth> {

    // Constantes
    public final static String EXIT = "Exit";
    public final static String ARRIVAL = "Arrival";

    // Atributes
    private String date;
    private String time;
    private String airline;
    private String codeFligth;
    private String city;
    private int gate;
    private String state;

    // Constructor
    public Fligth(String date, String time, String airline, String codeFligth, String city, int gate, String state) {
        this.date = date;
        this.time = time;
        this.airline = airline;
        this.codeFligth = codeFligth;
        this.city = city;
        this.gate = gate;
        this.state = state;
    }

    // Methods
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getCodeFligth() {
        return codeFligth;
    }

    public void setCodeFligth(String codeFligth) {
        this.codeFligth = codeFligth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int compareTo(Fligth fligth) {
        int comparations;
        if(this.time.compareTo(fligth.time)>0)
            comparations = 1;
        else if(this.time.compareTo(fligth.time)<0){
            comparations = -1;
        }else
            comparations = 0;
        return comparations;
    }

    @Override
    public int compare(Fligth o1, Fligth o2) {

        return 0;
    }
}
