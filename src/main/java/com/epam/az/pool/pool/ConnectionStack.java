package com.epam.az.pool.pool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionStack<E> {
    private List<E> list = new ArrayList<>();
    public void push(E e){
        list.add(e);
    }
    public E pop(){
        E e = list.get(list.size()-1);
        list.remove(list.size()-1);
        return e;
    }
    public boolean isEmpty(){
        if(list.size() == 0){
            return true;
        }
        return false;
    }
}

