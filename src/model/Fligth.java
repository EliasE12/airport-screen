package model;

import java.util.Comparator;

// Class
public class Fligth implements Comparable<Fligth>, Comparator<Fligth> {

    // Atributes
    private String date;
    private String time;
    private String airline;
    private String fligth;
    private String city;
    private int gate;
    private String state;

    // Constructor
    public Fligth(String date, String time, String airline, String fligth, String city, int gate, String state) {
        this.date = date;
        this.time = time;
        this.airline = airline;
        this.fligth = fligth;
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

    public String getFligth() {
        return fligth;
    }

    public void setFligth(String fligth) {
        this.fligth = fligth;
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
        int comparation;
        if(this.time.compareTo(fligth.time)>0)
            comparation = 1;
        else if(this.time.compareTo(fligth.time)<0){
            comparation = -1;
        }else
            comparation = 0;
        return comparation;
    }

    public int compareToDate(Fligth fligth){
        int comparation;
        if (this.date.compareTo(fligth.date)>0) {
            comparation = 1;
        }else if(this.date.compareTo(fligth.date)<0) {
            comparation = -1;
        }else {
            comparation = 1;
        }
        return comparation;
    }

    @Override
    public int compare(Fligth fligth1, Fligth fligth2) {

        return 0;
    }

    @Override
    public String toString() {
        return "Fligth{" +
                       "date='" + date + '\'' +
                       ", time='" + time + '\'' +
                       ", airline='" + airline + '\'' +
                       ", fligth='" + fligth + '\'' +
                       ", city='" + city + '\'' +
                       ", gate=" + gate +
                       ", state='" + state + '\'' +
                       '}';
    }
}
