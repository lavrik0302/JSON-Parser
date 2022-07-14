package com.intexsoft.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

@UtilityClass
public class CollectionToArray {
    public static <T> T[] collectionToArray(Class<T> tClass, Collection collection) {
        T[] elements = (T[]) Array.newInstance(tClass, collection.size());
        Iterator<T> iterator = collection.iterator();
        for (int i = 0; i < collection.size(); i++) {
            elements[i] = iterator.next();
        }
        return elements;
    }
}
