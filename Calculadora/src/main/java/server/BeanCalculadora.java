package server;

import java.util.Date;

public class BeanCalculadora {
    private long id;
    private String type;
    private double first_number;
    private double second_number;
    private double result;
    private Date create_ad;

    public BeanCalculadora() {
    }

    public BeanCalculadora(long id, String type, double first_number, double second_number, double result, Date create_ad) {
        this.id = id;
        this.type = type;
        this.first_number = first_number;
        this.second_number = second_number;
        this.result = result;
        this.create_ad = create_ad;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFirst_number() {
        return first_number;
    }

    public void setFirst_number(double first_number) {
        this.first_number = first_number;
    }

    public double getSecond_number() {
        return second_number;
    }

    public void setSecond_number(double second_number) {
        this.second_number = second_number;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Date getCreate_ad() {
        return create_ad;
    }

    public void setCreate_ad(Date create_ad) {
        this.create_ad = create_ad;
    }

    @Override
    public String toString() {
        return "BeanCalculadora{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", first_number=" + first_number +
                ", second_number=" + second_number +
                ", result=" + result +
                ", create_ad=" + create_ad +
                '}';
    }
}
