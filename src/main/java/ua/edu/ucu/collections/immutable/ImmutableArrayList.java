package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    private Object[] elements;

    public ImmutableArrayList() {
        elements = new Object[0];
    }

    public ImmutableArrayList(Object[] source) {
        elements = source.clone();
    }

    @Override
    public ImmutableList add(Object e) {
        return add(elements.length, e);
    }


    @Override
    public ImmutableList add(int index, Object e) {
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArr = new Object[elements.length + 1];
        System.arraycopy(elements, 0, newArr, 0, index);
        System.arraycopy(elements, index, newArr, index + 1, elements.length - index);
        newArr[index] = e;
        return new ImmutableArrayList(newArr);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(elements.length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > elements.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArr = new Object[elements.length + c.length];
        System.arraycopy(elements, 0, newArr, 0, index);
        System.arraycopy(c, 0, newArr, index, c.length);
        System.arraycopy(elements, index, newArr, index + c.length, elements.length - index);
        return new ImmutableArrayList(newArr);
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArr = new Object[elements.length - 1];
        System.arraycopy(elements, 0, newArr, 0, index);
        System.arraycopy(elements, index + 1, newArr, index, elements.length - index - 1);
        return new ImmutableArrayList(newArr);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        Object[] newArr = elements.clone();
        newArr[index] = e;
        return new ImmutableArrayList(newArr);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return elements.length == 0;
    }

    @Override
    public Object[] toArray() {
        return elements.clone();
    }

    @Override
    public String toString() {
        String str = Arrays.toString(toArray());
        return str.substring(1, str.length() - 1);
    }
}
