package org.example.MyListTest;

import java.util.Comparator;
import java.util.Objects;

public class Car implements Comparable<Car>{

    private String name;
    private int year;

    public Car(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Car o) {
        if(o != null){
            int result = this.name.compareTo(((Car) o).getName());
            if(result == 0){
                result = Comparator.<Integer>naturalOrder().compare(this.year, ((Car) o).year);
            }
            return result;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year);
    }
}
