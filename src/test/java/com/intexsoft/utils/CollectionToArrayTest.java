package test.java.com.intexsoft.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static main.java.com.intexsoft.utils.CollectionToArray.collectionToArray;

public class CollectionToArrayTest {

    @Test
    public void arrayListToArrayTest() {
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

    @Test
    public void linkedListToArrayTest() {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(0, 12);
        linkedList.add(1, 14);
        linkedList.add(2, 15);
        Integer[] array = collectionToArray(Integer.class, linkedList);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }

    @Test
    public void vectorToArrayTest() {
        List<Integer> vector = new Vector<>();
        vector.add(0, 12);
        vector.add(1, 14);
        vector.add(2, 15);
        Integer[] array = collectionToArray(Integer.class, vector);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }

    @Test
    public void stackToArrayTest() {
        List<Integer> stack = new Stack<>();
        stack.add(0, 12);
        stack.add(1, 14);
        stack.add(2, 15);
        Integer[] array = collectionToArray(Integer.class, stack);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }

    @Test
    public void arrayDequeToArrayTest() {
        ArrayDeque arrayDeque = new ArrayDeque<>();
        arrayDeque.add(12);
        arrayDeque.add(14);
        arrayDeque.add(15);
        Integer[] array = collectionToArray(Integer.class, arrayDeque);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }

    @Test
    public void hashSetToArrayTest() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(12);
        hashSet.add(14);
        hashSet.add(15);
        Integer[] array = collectionToArray(Integer.class, hashSet);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }

    @Test
    public void linkedHashSetToArrayTest() {
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(12);
        linkedHashSet.add(14);
        linkedHashSet.add(15);
        Integer[] array = collectionToArray(Integer.class, linkedHashSet);
        Integer[] actual = {12, 14, 15};
        Assert.assertSame(array[0], actual[0]);
        Assert.assertSame(array[1], actual[1]);
        Assert.assertSame(array[2], actual[2]);
    }
}