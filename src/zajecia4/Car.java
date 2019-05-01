package zajecia4;

import java.util.Objects;

public class Car implements Comparable<Car>{
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Car o) {
        return year-o.year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (year != car.year) return false;
        if (!brand.equalsIgnoreCase(car.brand)) return false;
        return model.equalsIgnoreCase(car.model);
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + model.toLowerCase().hashCode();
        result = 31 * result + year;
        return result;
    }
}
