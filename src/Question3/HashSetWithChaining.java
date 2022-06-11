/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question3;

import java.util.Objects;

/**
 *
 * @author ssr7324
 * @param <E>
 */
public class HashSetWithChaining<E> {

    class Node<E> {

        final int hash;
        final E key;

        Node<E> next;

        public Node(int hash, E key, Node<E> next) {
            this.hash = hash;
            this.key = key;
            this.next = next;
        }
    }

    private final int INITIAL_CAPACITY = 5;
    private final float LOAD_FACTOR = 0.75f;
    Node<E>[] table;
    private int size;

    public HashSetWithChaining() {
        table = (Node<E>[]) new Node[INITIAL_CAPACITY];
        size = 0;
    }

    private static int hash(Object key) {
        int hash;

        if (Objects.isNull(key)) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    private int hashCode(Object key) {
        return Objects.hashCode(key);
    }

    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    public E add(int hash, E key) {
        int index = hash % table.length;

        Node<E> newNode = new Node<E>(hash, key, null);

        if (Objects.isNull(table[index])) {
            table[index] = newNode;
        } else {

            Node<E> tempNode = table[index];
            Node<E> previousNode = null;

            while (Objects.nonNull(tempNode)) {
                if (tempNode.key.equals(key)) {
                    return key;
                }

                previousNode = tempNode;
                tempNode = tempNode.next;
            }

            previousNode.next = newNode;
        }

        size++;

        if (size >= table.length * LOAD_FACTOR) {

            int expandCapacity = (int) (table.length * (1.0 + LOAD_FACTOR));
            Node<E>[] expandTable = (Node<E>[]) new Node[expandCapacity];

            for (int i = 0; i < table.length; i++) {
                Node<E> headNode = table[i];

                if (Objects.isNull(headNode)) {
                    continue;
                }

                table[i] = null;

                Node<E> nextNode;

                while (Objects.nonNull(headNode)) {
                    int j = headNode.hash % expandCapacity;

                    if (Objects.nonNull(expandTable[j])) {
                        Node<E> tailNode = expandTable[j];

                        while (Objects.nonNull(tailNode.next)) {
                            tailNode = tailNode.next;
                        }

                        nextNode = headNode.next;
                        headNode.next = null;
                        tailNode.next = headNode;
                    } else {
                        nextNode = headNode.next;
                        headNode.next = null;
                        expandTable[j] = headNode;
                    }

                    headNode = nextNode;
                }
            }
            table = null;
            table = expandTable;
        }

        return null;
    }

    public void remove(int hash, Object o) {
        int index = hash % table.length;

        if (Objects.nonNull(table[index])) {

            if (table[index].key.equals(o)) {

                table[index] = table[index].next;

            } else {

                Node<E> currentNode = table[index];

                while (Objects.nonNull(currentNode.next)
                        && !currentNode.next.key.equals(o)) {

                    currentNode = currentNode.next;
                }

                if (Objects.nonNull(currentNode.next)) {
                    currentNode.next = currentNode.next.next;
                }
            }
        }
    }

    public boolean contains(Object o) {
        int index = hash(o) % table.length;

        Node<E> newNode = table[index];

        while (Objects.nonNull(newNode)) {
            if (o == newNode.key || (Objects.nonNull(o)
                    && o.equals(newNode.key))) {
                return true;
            }

            newNode = newNode.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        if (Objects.nonNull(table) && size > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < table.length; i++) {
            output += "Row" + i + ": ";

            if (Objects.nonNull(table[i])) {
                Node<E> node = table[i];

                while (Objects.nonNull(node)) {
                    output += node.key;
                    node = node.next;
                }
            }

            output += "\n";
        }
        return output;
    }
}
