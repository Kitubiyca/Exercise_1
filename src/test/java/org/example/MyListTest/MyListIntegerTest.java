package org.example.MyListTest;

import org.example.MyList;
import org.junit.*;

import java.util.Arrays;
import java.util.Comparator;

public class MyListIntegerTest {

    private MyList<Integer> myList;

    @Before
    public void setUp(){
        myList = new MyList<>();
        myList.add(1);
        myList.add(2);
        myList.add(-3);
        myList.add(3);
        myList.add(0);
        myList.add(-1);
        myList.add(-2);
        myList.add(2);
        myList.add(-3);
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
        myList.add(1);
        Assert.assertArrayEquals(new Integer[]{1, 2, -3, 3, 0, -1, -2, 2, -3, 1}, myList.toArray());
    }

    @Test
    public void addIndex(){
        myList.add(5, 4);
        Assert.assertArrayEquals(new Integer[]{1, 2, -3, 3, 0, 4, -1, -2, 2, -3}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addNegativeIndex() {
        myList.add(-1, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsIndex() {
        myList.add(65, 1);
    }

    @Test
    public void get() {
        Assert.assertEquals(0, (int) myList.get(4));
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
        Assert.assertEquals(2, (int) myList.remove(1));
        Assert.assertArrayEquals(new Integer[]{1, -3, 3, 0, -1, -2, 2, -3}, myList.toArray());
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
        Assert.assertArrayEquals(new Integer[]{}, myList.toArray());
    }

    @Test
    public void sort(){
        myList.sort(Comparator.<Integer>naturalOrder());
        Assert.assertArrayEquals(new Integer[]{-3, -3, -2, -1, 0, 1, 2, 2, 3}, myList.toArray());
    }

    @Test
    public void quicksort(){
        myList.quicksort(Comparator.<Integer>naturalOrder());
        Assert.assertArrayEquals(new Integer[]{-3, -3, -2, -1, 0, 1, 2, 2, 3}, myList.toArray());
    }

    @Test
    public void quicksortWithRange(){
        myList.quicksort(4, 9, Comparator.<Integer>naturalOrder());
        Assert.assertArrayEquals(new Integer[]{1, 2, -3, 3, -3, -2, -1, 0, 2}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void quicksortOutOfRange(){
        myList.quicksort(-1, 19, Comparator.<Integer>naturalOrder());
        Assert.assertArrayEquals(new Integer[]{1, 2, -3, 3, -3, -2, -1, 0, 2}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void quicksortEquals(){
        myList.quicksort(5, 5, Comparator.<Integer>naturalOrder());
        Assert.assertArrayEquals(new Integer[]{1, 2, -3, 3, -3, -2, -1, 0, 2}, myList.toArray());
    }

}