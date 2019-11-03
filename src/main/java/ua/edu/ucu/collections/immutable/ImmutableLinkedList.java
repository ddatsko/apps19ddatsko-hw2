package ua.edu.ucu.collections.immutable;

import java.util.HashMap;
import java.util.Map;

public class ImmutableLinkedList implements ImmutableList {
    private Map<Node, Node> links;
    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        private Object value;

        private Node(Object value) {
            this.value = value;
        }
    }

    public ImmutableLinkedList() {
        links = new HashMap<>();
        size = 0;
        head = null;
        tail = null;
    }

    public ImmutableLinkedList(Object[] source) {
        this();
        if (source.length != 0) {
            head = new Node(source[0]);
            links.put(head, null);
            Node currentNode = head;
            for (int i = 1; i < source.length; i++) {
                appendToNode(currentNode, new Node(source[i]));
                currentNode = links.get(currentNode);
            }
            size = source.length;
            tail = currentNode;
        }
    }

    private void appendToNode(Node node, Node newNode) {
        Node tmp = links.get(node);
        links.put(node, newNode);
        links.put(newNode, tmp);
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong linked List index");
        }
        if (index == size - 1) {
            return tail;
        }
        int counter;
        Node currentNode = head;
        for (counter = 0; counter < index; counter++) {
            currentNode = links.get(currentNode);
        }
        return currentNode;
    }


    private ImmutableLinkedList copy() {
        ImmutableLinkedList newList = new ImmutableLinkedList();
        newList.links = new HashMap<>(links.size());
        for (Map.Entry<Node, Node> entry : links.entrySet()) {
            newList.links.put(entry.getKey(), entry.getValue());
        }
        newList.size = size;
        newList.head = head;
        newList.tail = tail;
        return newList;
    }


    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        ImmutableLinkedList newList = copy();
        newList.size++;
        Node tmpNode = new Node(e);
        if (index == size && tail != null) {
            newList.links.put(newList.tail, tmpNode);
            newList.tail = tmpNode;
            return newList;
        }
        if (index == 0) {
            newList.links.put(tmpNode, newList.head);
            newList.head = tmpNode;
        } else {
            Node prev = newList.getNode(index - 1);
            newList.appendToNode(prev, tmpNode);
        }
        if (size == index) {
            newList.tail = tmpNode;
        }
        return newList;


    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (size == 0) {
            return new ImmutableLinkedList(c);
        }
        if (c.length == 0) {
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList newList = copy().add(index, c[0]);
        Node currentNode = newList.getNode(index);

        for (int i = 1; i < c.length; i++) {
            newList.appendToNode(currentNode, new Node(c[i]));
            currentNode = newList.links.get(currentNode);
        }
        if (index == size) {
            newList.tail = currentNode;
        }

        // -1 as one element was added before
        newList.size += c.length - 1;
        return newList;
    }

    @Override
    public Object get(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size - 1) {
            return tail.value;
        }
        return getNode(index).value;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong LinkedList Index");
        }
        ImmutableLinkedList newList = copy();
        Node prev;
        if (index == 0) {
            prev = null;
            newList.head = newList.links.get(head);
        } else {
            prev = newList.getNode(index - 1);
            newList.links.put(prev, newList.links.get(newList.links.get(prev)));
        }
        if (index == size - 1) {
            newList.tail = prev;
        }

        newList.size--;
        return newList;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableLinkedList newList = copy();
        Node tmp = new Node(e);
        if (index == 0) {
            newList.links.put(tmp, newList.links.get(newList.head));
            newList.head = tmp;
            return newList;
        }
        Node prev = newList.getNode(index - 1);
        newList.links.put(tmp, newList.links.get(newList.links.get(prev)));
        newList.links.put(prev, tmp);
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
            currentNode = links.get(currentNode);
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
    public String toString() {
        if (head == null) {
            return "";
        }
        StringBuilder res = new StringBuilder(head.value.toString());
        Node currentNode = head;
        while (links.get(currentNode) != null) {
            currentNode = links.get(currentNode);
            res.append(", ").append(currentNode.value.toString());

        }
        return res.toString();
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            res[i] = currentNode.value;
            currentNode = links.get(currentNode);
        }
        return res;
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
