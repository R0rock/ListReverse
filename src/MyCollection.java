/**
 * Интерфейс MyCollection описывает базовый набор методов,
 * необходимых для работы с коллекциями элементов.
 */
public interface MyCollection<E> {

    /**
     * Добавляет элемент в конец коллекции.
     *
     * element Элемент, который нужно добавить.
     */
    void add(E element);

    /**
     * Добавляет элемент по указанному индексу.
     *
     * index Индекс, куда нужно вставить элемент.
     * element Элемент, который нужно добавить.
     */
    void add(int index, E element);

    /**
     * Возвращает элемент по указанному индексу.
     *
     * index Индекс элемента.
     */
    E get(int index);

    /**
     * Удаляет элемент по указанному индексу.
     *
     * index Индекс удаляемого элемента.
     */
    E remove(int index);

    /**
     * Очищает коллекцию от всех элементов.
     */
    void clear();

    /**
     * Сортирует элементы коллекции в порядке возрастания.
     *
     */
    void sort();
}