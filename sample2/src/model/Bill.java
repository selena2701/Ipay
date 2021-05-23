package model;


public class Bill {
    private int id;
    private String date;
    private double value;

    public Bill(int id, String date, double value) {
        this.id = id;
        this.date = date;
        this.value = value;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
