package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    private Stack s;

    @Before
    public void setUp() {
        s = new Stack();
    }

    @Test
    public void testStack1() {
        int[] elements = new int[]{1, 2, 3, 4, 5};
        for (int i : elements) {
            s.push(i);
            assertEquals(i, s.peek());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(elements[4 - i], s.peek());
            assertEquals(elements[4 - i], s.pop());
        }
    }

    @Test
    public void testStack2() {
        int[] elements = new int[]{1, 1, 1, 1, 1, 1};
        for (int i : elements) {
            s.push(i);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(elements[i], s.peek());
            assertEquals(elements[i], s.pop());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testQueueError() {
        s.pop();
    }

}
