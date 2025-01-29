import java.util.Arrays;

/**
 * Класс MyArrayList реализует интерфейс MyCollection и представляет собой динамический массив.
 * Поддерживает добавление, удаление, получение элементов, а также сортировку и очистку коллекции.
 */
public class MyArrayList<E> implements MyCollection<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private static final String
            ErrorMessage = "Индекс вне допустимого диапазона: ";

                    /**
                     *  MyArrayList() – конструктор по умолчанию создает массив с начальной емкостью
                     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, позволяющий задать начальную емкость массива.
     *
     * initialCapacity Начальная емкость массива.
     */
    public MyArrayList(int initialCapacity) {
if (initialCapacity < 0) {
        throw new IllegalArgumentException("Начальная емкость должна быть положительной");
    }

    // Если емкость равна нулю, используем DEFAULT_CAPACITY
    int capacity = Math.max(initialCapacity, DEFAULT_CAPACITY);

    this.elements = new Object[capacity];
}


    @Override
    /**
     * Метод add(E element):
     *    Добавляет новый элемент в конец списка. Перед добавлением проверяется
     *    наличие свободного места в массиве методом ensureCapacity.
     */
    public void add(E element) {
        ensureCapacity(size + 1);// Убедимся, что есть достаточно места для нового элемента
        elements[size++] = element;
    }

    @Override
    /**
     * Метод add(int index, E element):
     *    Вставляет элемент по указанному индексу. При этом все последующие элементы сдвигаются вправо.
     *    Проверка индекса осуществляется через метод rangeCheckForAdd.
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    /**
     * Метод get(int index):
     *   Возвращает элемент по заданному индексу. Проверяет корректность индекса с помощью метода rangeCheck.
     */
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    @Override
    /**
     * Метод remove(int index):
     *    Удаляет элемент по указанному индексу. После удаления оставшиеся элементы сдвигаются влево,
     *    а последний элемент обнуляется для предотвращения утечек памяти.
     */
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = (E) elements[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
            elements[--size] = null; // Помечаем последний элемент как null

            return oldValue;

    }

    @Override
    /**
     * Метод clear():
     *    Очищает список, устанавливая все элементы массива в null и сбрасывая размер до нуля.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    /**
     * Метод sort():
     *   Сортирует элементы списка с использованием стандартного метода сортировки Arrays.sort.
     */
    public void sort() {
        Arrays.sort((E[]) elements, 0, size);
    }

            /**
             *Методы проверки индексов (rangeCheck, rangeCheckForAdd):
             * Эти методы используются для проверки того, находится ли указанный индекс внутри допустимых границ списка.
             */

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(ErrorMessage + index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(ErrorMessage + index);
        }
    }

    /**
     *Метод ensureCapacity(int minCapacity):
     *    - Этот метод увеличивает размер массива, если текущая емкость недостаточна для добавления новых элементов.
     *    Емкость увеличивается вдвое при необходимости.
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * метод grow(int minCapacity):
     *  гарантирует, что массив будет увеличен до необходимой минимальной емкости,
     *  обеспечивая возможность дальнейшего добавления элементов без перерасхода ресурсов.
     */
    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // Увеличение емкости вдвое
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    /**
     * Метод toString():
     *   Преобразует содержимое списка в строку вида [элемент1, элемент2, ...].
     */
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