package org.example.MyListTest;

import org.example.MyList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyListCarTest {

    private MyList<Car> myList;

    @Before
    public void setUp(){
        myList = new MyList<>();
        myList.add(new Car("Рено", 1998));
        myList.add(new Car("Лада", 2004));
        myList.add(new Car("Киа", 2003));
        myList.add(new Car("Опель", 2015));
        myList.add(new Car("Ламборгини", 2003));
        myList.add(new Car("Лада", 1993));
    }

    @Test(expected = NegativeArraySizeException.class)
    public void createNegativeLengthList() {
        MyList<Object> myList1 = new MyList<>(-2);
    }

    @Test
    public void createListWithCapacity() {
        MyList<Object> myList1 = new MyList<>(29);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void createListWithZeroCapacity() {
        MyList<Object> myList1 = new MyList<>(0);
    }

    @Test
    public void add(){
        myList.add(new Car("Хавал", 2023));
        Assert.assertArrayEquals(new Car[]{
                new Car("Рено", 1998),
                new Car("Лада", 2004),
                new Car("Киа", 2003),
                new Car("Опель", 2015),
                new Car("Ламборгини", 2003),
                new Car("Лада", 1993),
                new Car("Хавал", 2023)}, myList.toArray());
    }

    @Test
    public void addIndex(){
        myList.add(5, new Car("Хавал", 2023));
        Assert.assertArrayEquals(new Car[]{
                new Car("Рено", 1998),
                new Car("Лада", 2004),
                new Car("Киа", 2003),
                new Car("Опель", 2015),
                new Car("Ламборгини", 2003),
                new Car("Хавал", 2023),
                new Car("Лада", 1993)}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addNegativeIndex() {
        myList.add(-1, new Car("Хавал", 2023));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsIndex() {
        myList.add(65, new Car("Хавал", 2023));
    }

    @Test
    public void get() {
        Assert.assertEquals(new Car("Ламборгини", 2003), myList.get(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNegativeIndex() {
        myList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getEmptyElement() {
        myList.get(14);
    }

    @Test
    public void remove() {
        Assert.assertEquals(new Car("Лада", 2004), myList.remove(1));
        Assert.assertArrayEquals(new Car[]{
                new Car("Рено", 1998),
                new Car("Киа", 2003),
                new Car("Опель", 2015),
                new Car("Ламборгини", 2003),
                new Car("Лада", 1993)}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeNegativeIndex() {
        myList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeOutOfBoundsIndex() {
        myList.remove(14);
    }

    @Test
    public void clear() {
        myList.clear();
        Assert.assertArrayEquals(new Car[]{}, myList.toArray());
    }

    @Test
    public void sort(){
        myList.sort(Car::compareTo);
        Assert.assertArrayEquals(new Car[]{
                new Car("Киа", 2003),
                new Car("Лада", 1993),
                new Car("Лада", 2004),
                new Car("Ламборгини", 2003),
                new Car("Опель", 2015),
                new Car("Рено", 1998)}, myList.toArray());
    }

    @Test
    public void quicksort(){
        myList.sort(Car::compareTo);
        Assert.assertArrayEquals(new Car[]{
                new Car("Киа", 2003),
                new Car("Лада", 1993),
                new Car("Лада", 2004),
                new Car("Ламборгини", 2003),
                new Car("Опель", 2015),
                new Car("Рено", 1998)}, myList.toArray());    }

    @Test
    public void quicksortWithRange(){
        myList.quicksort(2, 6, Car::compareTo);
        Assert.assertArrayEquals(new Car[]{
                new Car("Рено", 1998),
                new Car("Лада", 2004),
                new Car("Киа", 2003),
                new Car("Лада", 1993),
                new Car("Ламборгини", 2003),
                new Car("Опель", 2015)}, myList.toArray());    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void quicksortOutOfRange(){
        myList.quicksort(-1, 19, Car::compareTo);
        Assert.assertArrayEquals(new Car[]{
                new Car("Киа", 2003),
                new Car("Лада", 1993),
                new Car("Лада", 2004),
                new Car("Ламборгини", 2003),
                new Car("Опель", 2015),
                new Car("Рено", 1998)}, myList.toArray());    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void quicksortEquals(){
        myList.quicksort(5, 5, Car::compareTo);
        Assert.assertArrayEquals(new Car[]{
                new Car("Киа", 2003),
                new Car("Лада", 1993),
                new Car("Лада", 2004),
                new Car("Ламборгини", 2003),
                new Car("Опель", 2015),
                new Car("Рено", 1998)}, myList.toArray());    }

}