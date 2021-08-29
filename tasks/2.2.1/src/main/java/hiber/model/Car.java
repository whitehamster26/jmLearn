package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    private String model;

    @Id
    private int series;

    public Car() {

    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return String.format("%s %d", model, series);
    }

}
