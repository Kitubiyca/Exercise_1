package org.example;

import java.util.Arrays;
import java.util.Comparator;

 /**
 * Реализует список для хранения дженерик данных
 */
public class MyList<T> {

    private int capacity = 8;
    private Object[] data;
    private int pointer = 0;

    /**
     * Создаёт список с заданным размером.
     *
     * @param capacity начальный размер создаваемого списка.
     *
     * @exception NegativeArraySizeException размер должен быть больше нуля.
     * */
    public MyList(int capacity) throws NegativeArraySizeException{
        this.capacity = capacity;
        if(capacity > 0){
            data = new Object[capacity];
        } else throw new NegativeArraySizeException();
    }

    /**
     * Создаёт список с размером по умолчанию (8 элементов).
     * */
    public MyList() {
        data = new Object[capacity];
    }

    /**
     * Добавляет новый элемент к списку.
     *
     * Если список достиг предельного размера при добавлении нового элемента, то он расширяется в два раза.
     *
     * @param element Добавляемый элемент.
     * */
    public void add(T element){
        if(pointer >= capacity){
            capacity *= 2;
            data = Arrays.copyOf(data, capacity);
        }
        data[pointer] = element;
        pointer++;
    }

     /**
      * Добавляет новый элемент в список на заданную позицию, при этом, все следующие за заданным индексом элементы смещаются на одну позицию.
      *
      * Если список достиг предельного размера при добавлении нового элемента, то он расширяется в два раза.
      *
      * @param index позиция добавляемого элемента.
      *
      * @param element добавляемый элемент.
      *
      * @throws ArrayIndexOutOfBoundsException index должен находится в пределах списка.
      * */
    public void add(int index, T element) throws ArrayIndexOutOfBoundsException{
        if(index < 0 || index > pointer) throw new ArrayIndexOutOfBoundsException();
        if(pointer >= capacity){
            capacity *= 2;
            data = Arrays.copyOf(data, capacity);
        }
        System.arraycopy(data, index, data, index + 1, pointer - index);
        data[index] = element;
        pointer++;
    }

    /**
     * Возвращает элемент, распологающийся по заданному индексу.
     *
     * @param index номер элемента.
     *
     * @return объект по заданному индексу.
     *
     * @throws IndexOutOfBoundsException возвращает исключение при выходе за пределы списка.
     * */
    public T get(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= pointer) throw new ArrayIndexOutOfBoundsException();
        return (T) data[index];
    }

     /**
      * Удаляет элемент, распологающийся по заданному индексу. При это происходит смещение списка для заполенения свободного места.
      *
      * Уменьшает размер массива в два раза, при освобождение достаточного количества свободного места.
      *
      * @param index номер элемента.
      *
      * @return возвращает удалённый элемент.
      *
      * @throws ArrayIndexOutOfBoundsException возвращает исключение при выходе за пределы списка.
      * */
    public T remove(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0 || index >= pointer) throw new ArrayIndexOutOfBoundsException();
        T element = (T) data[index];
        for (int i = index; i < pointer; i++) {
            data[i] = data[i+1];
        }
        if(pointer < capacity / 2){
            capacity /= 2;
            data = Arrays.copyOf(data, capacity);
        }
        pointer--;
        return element;
    }

    /**
     * Очищает список и устанавливает все значения по умолчанию
     * */
    public void clear(){
        capacity = 8;
        pointer = 0;
        data = new Object[capacity];
    }

    /**
     * Сортирует список с использованием переданного компаратора. используют стандартные средства сортировки.
     *
     * @param comparator компаратор для сортировки.
     * */
    public void sort(Comparator<T> comparator){
        Arrays.sort((T[])data, 0, pointer, comparator);
    }

     /**
      * Сортирует список с использованием переданного компаратора. используют метод сортировки quicksort.
      *
      * @param comparator компаратор для сортировки.
      * */
    public void quicksort(Comparator<T> comparator){
        quicksort(0, pointer, comparator);
    }

     /**
      * Сортирует список с использованием переданного компаратора. используют метод сортировки quicksort.
      *
      * @param comparator компаратор для сортировки.
      *
      * @param startIndex позиция начала сортировки (включительно).
      *
      * @param endIndex позиция окончания сортировки (не включительно).
      *
      * @throws IndexOutOfBoundsException при выходе за пределы списка.
      * */
    public void quicksort(int startIndex, int endIndex, Comparator<T> comparator) throws IndexOutOfBoundsException{
        endIndex--;
        if(startIndex < 0 || startIndex >= pointer || endIndex < 0 || endIndex >= pointer || startIndex >= endIndex) throw new IndexOutOfBoundsException();
        int pivotIndex = (endIndex + startIndex) / 2;
        Object pivot = data[pivotIndex];
        data[pivotIndex] = data[endIndex];
        int leftItemIndex = endIndex, rightItemIndex = startIndex;
        while(true){
            for(int i = startIndex; i < endIndex; i++){
                if(comparator.compare((T) data[i], (T) pivot) > 0){
                    leftItemIndex = i;
                    break;
                }
            }
            for(int i = endIndex-1; i >= startIndex; i--){
                if(comparator.compare((T) data[i], (T) pivot) < 0){
                    rightItemIndex = i;
                    break;
                }
            }
            if(leftItemIndex < rightItemIndex){
                Object buff = data[leftItemIndex];
                data[leftItemIndex] = data[rightItemIndex];
                data[rightItemIndex] = buff;
                leftItemIndex = endIndex;
                rightItemIndex = startIndex;
            } else break;
        }
        if(leftItemIndex != endIndex){
            data[endIndex] = data[leftItemIndex];
            data[leftItemIndex] = pivot;
            pivotIndex = leftItemIndex;
        } else {
            data[endIndex] = pivot;
            pivotIndex = endIndex;
        }
        if(pivotIndex - startIndex > 1) quicksort(startIndex, pivotIndex, comparator);
        if(endIndex - pivotIndex > 1) quicksort(pivotIndex+1, endIndex+1, comparator);
    }

    /**
     * Возвращает строку со всеми элементами списка. Использует на элементах метод toString().
     *
     * @return строковое представление списка.
     * */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("[");
        for (int i = 0; i < pointer; i++) {
            resultString.append(data[i].toString());
            resultString.append(", ");
        }
        if(resultString.length() > 1) resultString.delete(resultString.length()-2, resultString.length());
        resultString.append("]");
        return resultString.toString();
    }

     /**
      * Возвращает массив со всеми элементами списка. Размер равен количеству элементов.
      *
      * @return возвращаемый массив.
      * */
    public T[] toArray() {
        return (T[]) Arrays.copyOf(data, pointer);
    }
}
