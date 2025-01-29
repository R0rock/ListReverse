public class Main {

    public static void main(String[] args) {
        testMyArrayList();
        testMyLinkedList();
    }

    private static void testMyArrayList() {
        System.out.println("Тестирование MyArrayList:");

        MyArrayList<Integer> myArrayList = new MyArrayList<>();

        // Добавляем несколько элементов
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);

        System.out.println("Исходная коллекция: " + myArrayList);

        // Получаем элемент по индексу
        Integer secondElement = myArrayList.get(1);
        System.out.println("Второй элемент: " + secondElement);

        // Вставляем новый элемент по индексу
        myArrayList.add(1, 100);
        System.out.println("После вставки элемента по индексу: " + myArrayList);

        // Удаляем элемент по индексу
        Integer removedElement = myArrayList.remove(2);
        System.out.println("Удалённый элемент: " + removedElement);
        System.out.println("После удаления элемента: " + myArrayList);

        // Сортируем коллекцию
        myArrayList.sort();
        System.out.println("Отсортированная коллекция: " + myArrayList);

        // Очищаем коллекцию
        myArrayList.clear();
        System.out.println("Очищенная коллекция: " + myArrayList);
    }

    private static void testMyLinkedList() {
        System.out.println("\nТестирование MyLinkedList:");

        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        // Добавляем несколько строк
        myLinkedList.add("apple");
        myLinkedList.add("banana");
        myLinkedList.add("cherry");
        myLinkedList.add("date");

        System.out.println("Исходная коллекция: " + myLinkedList);

        // Получаем элемент по индексу
        String firstElement = myLinkedList.get(0);
        System.out.println("Первый элемент: " + firstElement);

        // Вставляем новый элемент по индексу
        myLinkedList.add(1, "grape");
        System.out.println("После вставки элемента по индексу: " + myLinkedList);

        // Удаляем элемент по индексу
        String removedElement = myLinkedList.remove(2);
        System.out.println("Удалённый элемент: " + removedElement);
        System.out.println("После удаления элемента: " + myLinkedList);

        // Сортируем коллекцию
        myLinkedList.sort();
        System.out.println("Отсортированная коллекция: " + myLinkedList);

        // Очищаем коллекцию
        myLinkedList.clear();
        System.out.println("Очищенная коллекция: " + myLinkedList);
    }
}