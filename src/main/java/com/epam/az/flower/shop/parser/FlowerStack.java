package com.epam.az.flower.shop.parser;

import java.util.ArrayList;
import java.util.List;

public class FlowerStack<E> {
    private List<E> values = new ArrayList<>();

    public E pop() {
        if (values.size() == 1) {
            return values.get(0);
        } else {
            E e = values.get(values.size() - 1);
            values.remove(values.size() - 1);
            return e;
        }
    }

    public void push(E item) {
        values.add(item);
    }

    public E getLast() {
        if (values.size() > 1)
            return values.get(values.size() - 1);
        return values.get(0);
    }

    public List<E> getValues() {
        return values;
    }

    public int size() {
        return values.size();
    }
}
