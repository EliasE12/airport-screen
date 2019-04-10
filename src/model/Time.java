package model;

public class Time {

    private int day;
    private int month;
    private String momentDay;

    public Time(int day, int month, String momentDay) {
        this.day = day;
        this.month = month;
        this.momentDay = momentDay;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getMomentDay() {
        return momentDay;
    }

    public void setMomentDay(String momentDay) {
        this.momentDay = momentDay;
    }

    @Override
    public String toString() {
        return day+":"+month+":"+momentDay;
    }


}
