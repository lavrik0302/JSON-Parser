package com.intexsoft.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static com.intexsoft.utils.CollectionToArray.collectionToArray;

public class CollectionToArrayTest {

    @Test
    public void collectionToArrayTest() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(0, 12);
        arrayList.add(1, 14);
        arrayList.add(2, 15);
        Integer[] array = collectionToArray(Integer.class, arrayList);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }
}