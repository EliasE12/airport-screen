package model;

// Class
public class Fligth implements Comparable<Fligth>{

    // Atributes
    private String date;
    private String time;
    private String airline;
    private String fligth;
    private String city;
    private int gate;
    private String state;

    private Fligth next;

    // Constructor
    public Fligth(){}

    public Fligth(String date, String time, String airline, String fligth, String city, int gate, String state) {
        this.date = date;
        this.time = time;
        this.airline = airline;
        this.fligth = fligth;
        this.city = city;
        this.gate = gate;
        this.state = state;

        next = null;

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

    public Fligth getNext() {
        return next;
    }

    public void setNext(Fligth next) {
        this.next = next;
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
