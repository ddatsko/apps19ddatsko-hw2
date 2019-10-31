package ua.edu.ucu.collections.immutable;


import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest extends TestCase {
    private ImmutableArrayList emptyList;
    private ImmutableArrayList smallList;
    private ImmutableArrayList largeList;
    private Object[] sampleList = new Object[]{10, 11, 12};


    @Override
    protected void setUp() throws Exception {
        smallList = new ImmutableArrayList(new Object[]{1, 2, 3});
        emptyList = new ImmutableArrayList();
        largeList = new ImmutableArrayList(new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testListAdd() {
        ImmutableList actual = smallList.add(1);
        Object[] expected = new Object[]{1, 2, 3, 1};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(smallList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testListAddIndex() {
        ImmutableList actual = smallList.add(1, 5);
        Object[] expected = new Object[]{1, 5, 2, 3};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(smallList.toArray(), new Object[]{1, 2, 3});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListAddIndexOutOfRange() {
        ImmutableList actual = smallList.add(10, 5);
    }

    @Test
    public void testListAddAll() {
        ImmutableList actual = smallList.addAll(sampleList);
        Object[] expected = new Object[]{1, 2, 3, 10, 11, 12};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(smallList.toArray(), new Object[]{1, 2, 3});
    }

    @Test
    public void testListAddAllIndex() {
        ImmutableList actual = smallList.addAll(1, sampleList);
        Object[] expected = new Object[]{1, 10, 11, 12, 2, 3};
        assertArrayEquals(actual.toArray(), expected);
        assertArrayEquals(smallList.toArray(), new Object[]{1, 2, 3});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListAddAllIndexOutOfRange() {
        ImmutableList actual = smallList.addAll(10, sampleList);
    }

}
