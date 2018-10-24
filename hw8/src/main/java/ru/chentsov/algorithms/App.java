package ru.chentsov.algorithms;

public class App
{

    public static void main( String[] args )
    {
        HashTable<Integer, Cat> ht = new HashTable<>(10);
        ht.insert(13, new Cat(13, ""));
        ht.insert(23, new Cat(23, ""));
        ht.insert(32, new Cat(32, ""));
        ht.insert(42, new Cat(42, ""));
        ht.insert(54, new Cat(54, ""));
        ht.insert(64, new Cat(64, ""));
        ht.insert(78, new Cat(78, ""));
        ht.insert(88, new Cat(88, ""));
        ht.insert(125, new Cat(125, ""));
        ht.insert(1, new Cat(1, ""));
        System.out.println(ht);
        ht.remove(78);
        System.out.println(ht);
        System.out.println(ht.find(42));
    }

}
