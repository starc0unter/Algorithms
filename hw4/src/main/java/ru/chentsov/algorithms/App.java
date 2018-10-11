package ru.chentsov.algorithms;


import java.util.LinkedList;

public class App
{
    public static void main( String[] args )
    {
        LinkedList<Cat> l = new LinkedList<>();

        ReversibleLinkedList list = new ReversibleLinkedList();
        Cat cat = new Cat(3, "cat-3");

        insertHeadPack(list, 5);
        System.out.println("InsertHeadPack: " + list + "\n");

        list.removeTail();
        System.out.println("RemoveTail: " + list + "\n");

        list.removeHead();
        System.out.println("RemoveHead: " + list + "\n");

        list.insertTail(new Cat(1, "new cat-1"));
        list.insertHead(new Cat(5, "new cat-5"));
        System.out.println("Insert tail and head: " + list + "\n");
        while (!list.isEmpty()) list.removeHead();

        insertTailPack(list, 5);
        System.out.println("InsertTailPack: " + list + "\n");
        System.out.println("Contains " + cat + "? " + list.contains(cat) + "\n");

        list.delete(cat);
        System.out.println("After " + cat + " deletion: " + list);
        while (!list.isEmpty()) list.removeTail();

        System.out.println("\n---Iterators---\n");

        insertHeadPack(list, 5);
        ReversibleListIterator iterator = list.iterator();
        iterator.insertBefore(new Cat(0, "just born Cat"));
        iterator.insertAfter(new Cat(6, "very old Cat"));
        iterator.reset();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        iterator.reset();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.next();
        iterator.remove();
        System.out.println("\n\nRemoved element by iterator: " + list);
    }

    public static void insertHeadPack (ReversibleLinkedList list, int size) {
        for (int i = 0; i < size; i++) {
            list.insertHead(new Cat((i + 1), "cat-" + (i + 1)));
        }
    }

    public static void insertTailPack (ReversibleLinkedList list, int size) {
        for (int i = 0; i < size; i++) {
            list.insertTail(new Cat((i + 1), "cat-" + (i + 1)));
        }
    }

}
