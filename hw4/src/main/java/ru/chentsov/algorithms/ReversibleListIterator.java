package ru.chentsov.algorithms;

import java.util.Iterator;

public interface ReversibleListIterator extends Iterator<Cat> {

    void insertBefore(Cat c);
    void insertAfter(Cat c);
    void reset();

}
