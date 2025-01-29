import java.util.Arrays;

/**
 * Класс MyLinkedList реализует интерфейс MyCollection и представляет собой двусвязный список.
 * Поддерживает добавление, удаление, получение элементов, а также сортировку и очистку коллекции.
 */
public class MyLinkedList<E> implements MyCollection<E> {
    // Поля класса
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Конструкторы
    public MyLinkedList() {}

    // Методы класса
    @Override
    /**
     * Метод add(E element):
     *    Добавляет элемент в конец списка, вызывая метод linkLast.
     */
    public void add(E element) {
        linkLast(element);
    }

    @Override
    /**
     * Метод add(int index, E element):
     *    Вставляет элемент по указанному индексу.
     *    Проверяет границы индекса и вызывает либо linkLast для вставки в конец,
     *    либо linkBefore для вставки перед указанным узлом.
     */
    public void add(int index, E element) {
        checkIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    /**
     * Метод get(int index):
     *    Возвращает элемент по заданному индексу после проверки границ.
     */
    public E get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    @Override
    /**
     * Метод remove(int index):
     *    Удаляет узел по указанному индексу и возвращает значение удаленного элемента.
     */
    public E remove(int index) {
        checkIndex(index);
        return unlink(node(index));
    }

    @Override
    /**
     * Метод clear():
     *    Очищает список, обнуляя все ссылки узлов и устанавливая head и tail в null.
     */
    public void clear() {
        for (Node<E> x = head; x != null;) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    /**
     * Метод sort():
     *    Сортирует элементы списка, преобразуя их в массив, сортируя массив через
     *    Arrays.sort и затем заново добавляя отсортированные элементы в список.
     */
    public void sort() {
        Object[] array = toArray();
        Arrays.sort(array);
        clear();
        for (Object o : array) {
            add((E) o);
        }
    }

    // Внутренний класс Node
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * checkIndex проверяет, находится ли индекс внутри допустимых границ.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * node находит узел по индексу, начиная поиск либо с начала, либо с конца списка в
     * зависимости от того, где индекс ближе к середине.
     */
    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * linkFirst, linkLast, linkBefore добавляют новый узел соответственно в начало, конец или перед указанным узлом.
     */

    private void linkFirst(E e) {
        final Node<E> f = head;
        final Node<E> newNode = new Node<>(null, e, f);
        head = newNode;
        if (f == null)
            tail = newNode;
        else
            f.prev = newNode;
        size++;
    }

    private void linkLast(E e) {
        final Node<E> l = tail;
        final Node<E> newNode = new Node<>(l, e, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        }else {
            l.next = newNode;
            size++;
        }
    }

    private void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            head = newNode;
        }else {
            pred.next = newNode;
        }
        size++;
    }

    /**
     * unlink удаляет указанный узел из списка.
     */
    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    /**
     * toArray преобразует список в массив объектов.
     */
    private Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    /**
     * toString формирует строковое представление списка.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.item);
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}