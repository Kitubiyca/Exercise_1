package org.example.MyListTest;

import org.example.MyList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

public class MyListStringTest {

    private MyList<String> myList;

    @Before
    public void setUp(){
        myList = new MyList<>();
        myList.add("Миша");
        myList.add("Маша");
        myList.add("Ярослав");
        myList.add("Саша");
        myList.add("Аня");
        myList.add("Гриша");
        myList.add("Маша");
        myList.add("Ваня");
        myList.add("Женя");
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
        myList.add("Влад");
        Assert.assertArrayEquals(new String[]{"Миша", "Маша", "Ярослав", "Саша", "Аня", "Гриша", "Маша", "Ваня", "Женя", "Влад"}, myList.toArray());
    }

    @Test
    public void addIndex(){
        myList.add(5, "Стас");
        Assert.assertArrayEquals(new String[]{"Миша", "Маша", "Ярослав", "Саша", "Аня", "Стас", "Гриша", "Маша", "Ваня", "Женя"}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addNegativeIndex() {
        myList.add(-1, "Стас");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addOutOfBoundsIndex() {
        myList.add(65, "Стас");
    }

    @Test
    public void get() {
        Assert.assertEquals("Аня", myList.get(4));
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
        Assert.assertEquals("Маша", myList.remove(1));
        Assert.assertArrayEquals(new String[]{"Миша", "Ярослав", "Саша", "Аня", "Гриша", "Маша", "Ваня", "Женя"}, myList.toArray());
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
        Assert.assertArrayEquals(new String[]{}, myList.toArray());
    }

    @Test
    public void sort(){
        myList.sort(Comparator.<String>naturalOrder());
        Assert.assertArrayEquals(new String[]{"Аня", "Ваня", "Гриша", "Женя", "Маша", "Маша", "Миша", "Саша", "Ярослав"}, myList.toArray());
    }

    @Test
    public void quicksort(){
        myList.quicksort(Comparator.<String>naturalOrder());
        Assert.assertArrayEquals(new String[]{"Аня", "Ваня", "Гриша", "Женя", "Маша", "Маша", "Миша", "Саша", "Ярослав"}, myList.toArray());}

    @Test
    public void quicksortWithRange(){
        myList.quicksort(4, 9, Comparator.<String>naturalOrder());
        Assert.assertArrayEquals(new String[]{"Миша", "Маша", "Ярослав", "Саша", "Аня", "Ваня", "Гриша", "Женя", "Маша"}, myList.toArray());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void quicksortOutOfRange(){
        myList.quicksort(-1, 19, Comparator.<String>naturalOrder());
        Assert.assertArrayEquals(new String[]{"Аня", "Ваня", "Гриша", "Женя", "Маша", "Маша", "Миша", "Саша", "Ярослав"}, myList.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void quicksortEquals(){
        myList.quicksort(5, 5, Comparator.<String>naturalOrder());
        Assert.assertArrayEquals(new String[]{"Аня", "Ваня", "Гриша", "Женя", "Маша", "Маша", "Миша", "Саша", "Ярослав"}, myList.toArray());
    }

}