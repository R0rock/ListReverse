import java.util.Arrays;

/**
 * Класс MyArrayList реализует интерфейс MyCollection и представляет собой динамический массив.
 * Поддерживает добавление, удаление, получение элементов, а также сортировку и очистку коллекции.
 */
public class MyArrayList<E> implements MyCollection<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, позволяющий задать начальную емкость массива.
     *
     * initialCapacity Начальная емкость массива.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Начальная емкость должна быть положительной");
        }
    }

    @Override
    public void add(E element) {
        ensureCapacity(size + 1);// Убедимся, что есть достаточно места для нового элемента
        elements[size++] = element;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = (E) elements[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null; // Помечаем последний элемент как null

        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void sort() {
        Arrays.sort((E[]) elements, 0, size);
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // Увеличение емкости вдвое
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}