package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private int size;
    private Node head;

    private static class Node {
        private Object value;
        private Node next;

        private Node(Object value) {
            this.value = value;
            next = null;
        }

        private Node(Object value, Node next) {
            this(value);
            this.next = next;
        }
    }

    public ImmutableLinkedList(Object[] source) {
        if (source.length != 0) {
            head = new Node(source[0]);
            Node currentNode = head;
            for (int i = 1; i < source.length; i++) {
                currentNode.next = new Node(source[i]);
                currentNode = currentNode.next;

            }
        }
        size = source.length;
    }

    public ImmutableLinkedList() {
        head = null;
        size = 0;
    }

    private ImmutableLinkedList copyOf() {
        if (size == 0) {
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList newList = new ImmutableLinkedList();
        newList.size = size;
        newList.head = new Node(head.value);
        Node currentNode = head.next;
        Node currentAddingNode = newList.head;
        while (currentNode != null) {
            currentAddingNode.next = new Node(currentNode.value);
            currentAddingNode = currentAddingNode.next;
            currentNode = currentNode.next;
        }
        return newList;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        ImmutableLinkedList newList = copyOf();
        if (index == 0) {
            newList.head = new Node(e, newList.head);
        } else {
            Node before = newList.getNode(index - 1);
            before.next = new Node(e, before.next);
        }
        newList.size = size + 1;
        return newList;
    }

    // Find the Node by given index
    private Node getNode(int index) {
        if (index < 0 || index > size || size == 0) {
            throw new IndexOutOfBoundsException();
        }
        int counter;
        Node currentNode = head;
        for (counter = 0; counter < index; counter++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList newList = copyOf();

        // Add A Node to avoid some problems with adding elements to the head
        newList.head = new Node(null, newList.head);
        if (c.length > 0) {
            Node currentAdding = newList.getNode(index);
            for (Object o : c) {
                currentAdding.next = new Node(o, currentAdding.next);
                currentAdding = currentAdding.next;
            }
        }

        // remove the added Node
        newList.head = newList.head.next;

        newList.size = size + c.length;
        return newList;
    }

    @Override
    public Object get(int index) {
        return getNode(index).value;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        ImmutableLinkedList newList = copyOf();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            newList.head = newList.head.next;
        } else {
            Node before = newList.getNode(index - 1);
            before.next = before.next.next;
        }
        newList.size--;
        return newList;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList newList = copyOf();
        newList.getNode(index).value = e;
        return newList;
    }

    @Override
    public int indexOf(Object e) {
        int counter = 0;
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value == e) {
                return counter;
            }
            counter++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int currentPos = 0;
        Node currentNode = head;
        while (currentNode != null) {
            result[currentPos] = currentNode.value;
            currentNode = currentNode.next;
            currentPos++;
        }
        return result;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "";
        }

        StringBuilder res = new StringBuilder(head.value.toString());
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            res.append(", ").append(currentNode.value.toString());

        }
        return res.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(size - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size - 1);
    }


}
