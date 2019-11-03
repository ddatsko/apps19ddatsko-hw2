package ua.edu.ucu.collections.immutable;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private ImmutableArrayList emptyList;
    private ImmutableArrayList smallList;
    private ImmutableArrayList largeList;
    private Object[] sampleList = new Object[]{10, 11, 12};


    @Before
    public void setUp() throws Exception {
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListAddNegativeIndex() {
        ImmutableList actual = smallList.add(-5, 5);
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListAddAllNegativeIndex() {
        ImmutableList actual = smallList.addAll(-5, new Object[]{1, 2});
    }


    @Test
    public void testListGet() {
        assertEquals(largeList.get(3), 4);
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListGetError() {
        largeList.get(10);
    }

    @Test
    public void testListRemove() {
        ImmutableList actual = largeList.remove(2);
        assertArrayEquals(actual.toArray(), new Object[]{1, 2, 4, 5, 6, 7, 8});
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListRemoveError() {
        ImmutableList actual = largeList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListRemoveNegativeIndex() {
        ImmutableList actual = smallList.remove(-5);
    }

    @Test
    public void testListSet() {
        ImmutableList actual = largeList.set(2, 10);
        assertArrayEquals(actual.toArray(), new Object[]{1, 2, 10, 4, 5, 6, 7, 8});
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListSetError() {
        ImmutableList actual = largeList.set(-1, 1);
    }

    @Test
    public void testListIndexOfExists() {
        int result = largeList.indexOf(2);
        assertEquals(result, 1);
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testListIndexOfNotExist() {
        int result = largeList.indexOf(100);
        assertEquals(result, -1);
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testListSize() {
        assertEquals(largeList.size(), 8);
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testListSizeEmpty() {
        assertEquals(emptyList.size(), 0);
    }

    @Test
    public void testListClear() {
        ImmutableList result = largeList.clear();
        assertArrayEquals(result.toArray(), new Object[0]);
        assertArrayEquals(largeList.toArray(), new Object[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testListClearEmpty() {
        ImmutableList result = emptyList.clear();
        assertArrayEquals(result.toArray(), new Object[0]);
    }

    @Test
    public void testIsEmptyEmpty() {
        assertFalse(largeList.isEmpty());
    }

    @Test
    public void testIsEmptyNotEmpty() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    public void testToStringEmpty() {
        assertEquals(emptyList.toString(), "");
    }

    @Test
    public void testToString() {
        assertEquals(smallList.toString(), "1, 2, 3");
    }


}
