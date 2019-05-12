package com.test.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apoorv on 06/05/19.
 *
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class LRUCache {
    public static void main(String[] args) {
        LRUCacheImpl<String, String> cache = new LRUCacheImpl<>(5);
        cache.put("k1", "V1");
        cache.printList();
        cache.put("k2", "V2");
        cache.printList();
        cache.put("k3", "V3");
        cache.printList();
        cache.put("k4", "V4");
        cache.printList();
        cache.put("k5", "V5");
        cache.printList();
        cache.put("k6", "V6");
        cache.printList();

        cache.get("k4");
        cache.printList();
    }
}

class LRUCacheImpl<K, V> {

    // Number of maximum keys cache can hold.
    // Default to 100.
    private int maxEntries = 100;

    // Datastructure to hold hash based key semantics for faster search.
    private Map<K, DoublyLinkedList<K, V>> map;

    // Maintain head and tail pointers for addition and eviction.
    private DoublyLinkedList<K, V> head;
    private DoublyLinkedList<K, V> tail;


    public LRUCacheImpl(int maxEntries) {
        this.maxEntries = maxEntries;
        this.map = new HashMap<>();
    }

    //@Override
    public void put (K key, V value) {
        // Check if max entries limit is reached then evict and put.
        if (map.size() == maxEntries) {
            // Evict the least recently used.
            K oldKey = removeLeastRecentlyNode();
            // Remove from map.
            map.remove(oldKey);
        }

        // Put in fromt of the queue.
        DoublyLinkedList<K, V> newValue = new DoublyLinkedList<>();
        newValue.setKey(key);
        newValue.setValue(value);
        // Add to queue.
        addNewNode(newValue);
        // Place on map.
        map.put(key, newValue);
    }

    //@Override
    public V get(K key) {
        // Check if already exists then move ref and return value.
        if (map.containsKey(key)) {
            // Move ref and return.
            DoublyLinkedList<K, V> ref = map.get(key);
            // Move the ref to front of list.
            moveNodeToFront(ref);
            // Return right value.
            return ref.getValue();
        }

        return null;
    }

    private void moveNodeToFront(DoublyLinkedList<K, V> ref) {
        if (ref != null) {
            // Consider if ref is front of the list.
            if (ref.getPrev() == null) {
                // No need to move as node is in front of the list.
                return;
            }

            // Make the prev node point to ref next node.
            ref.getPrev().setNext(ref.getNext());
            // Consider if ref is last of the list.
            if (ref.getNext() != null) {
                ref.getNext().setPrev(ref.getPrev());
            }

            // Maybe add check for list size, evaluate later.
            ref.setNext(head);
            ref.setPrev(null);
            head.setPrev(ref);
            // Update head pointer.
            head = ref;
        }
    }

    private K removeLeastRecentlyNode() {
        // Safe check, though not required.
        if (tail != null) {
            // Safe check, Check if only entry.
            if (tail.getPrev() != null) {
                tail.getPrev().setNext(null);
                tail = tail.getPrev();
            }

            return tail.getKey();
        }

        // Throw exception.
        return null;
    }

    private void addNewNode(DoublyLinkedList<K, V> ref) {
        // Safe check.
        if (ref != null) {
            // Check if head is null.
            if (head == null) {
                // Update head and tail for first insertion.
                head = ref;
                tail = ref;
                return;
            }

            // Place in-front of list.
            ref.setNext(head);
            head.setPrev(ref);
            head = ref;
        }
    }

    // Test method to check data.
    public void printList() {
        DoublyLinkedList<K, V> ref = head;
        while (ref != null) {
            System.out.println("Key: " + ref.getKey() + ", value: " + ref.getValue());
            ref = ref.getNext();
        }
        System.out.println("*************************");
    }
}

class DoublyLinkedList<K, V> {

    private K key;

    private V value;

    private DoublyLinkedList<K, V> prev;

    private DoublyLinkedList<K, V> next;

    // Getter setters.
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public DoublyLinkedList<K, V> getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedList<K, V> prev) {
        this.prev = prev;
    }

    public DoublyLinkedList<K, V> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedList<K, V> next) {
        this.next = next;
    }

}