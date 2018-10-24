package ru.chentsov.algorithms;

import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> {

    private int arrSize;
    private List<Node<K, V>>[] buckets;

    public HashTable(int arrSize) {
        this.arrSize = arrSize;
        buckets = (List<Node<K, V>>[]) new List<?>[arrSize];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrSize; i++) {
            if (buckets[i] == null) sb.append("* ");
            else buckets[i].forEach(n -> sb.append(n.getValue()).append(" "));
        }
        return sb.toString();
    }

    public V find(K key) {
        int hash = key.hashCode() % arrSize;
        if (buckets[hash] == null) return null;
        for (Node<K, V> n : buckets[hash]) {
            if (n.getKey().equals(key)) return n.getValue();
        }
        return null;
    }

    public void insert(K key, V value) {
        int hash = key.hashCode() % arrSize;
        if (buckets[hash] == null) {
            buckets[hash] = new LinkedList<>();
            buckets[hash].add(new Node<>(key, value));
            return;
        }
        for (Node<K, V> n : buckets[hash]) {
            if (n.getKey().equals(key)) {
                n.setValue(value);
                return;
            }
        }
        buckets[hash].add(new Node<>(key, value));
    }

    public V remove(K key) {
        int hash = key.hashCode() % arrSize;
        if (buckets[hash] == null) return null;
        for (int i = 0; i < buckets[hash].size(); i++) {
            if (buckets[hash].get(i).getKey().equals(key))
                return buckets[hash].remove(i).getValue();
        }
        return null;
    }

}