package com.prince;

/**
 * @Description 实现一个简单的HashMap
 * @Author prince Chen
 * @Date 2019/12/4 21:16
 */

public class HashMapV1<K, V> {
    private Node[] nodes = new Node[16];
    private int size;

    public V get(K key) {
        int index = indexOf(key);
        if (index < nodes.length) {
            return (V) nodes[index].v;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = indexOf(key);
        if (index == nodes.length) {
            nodes[size++] = new Node(key, value);
        } else {
            nodes[index].v = value;
        }
    }

    private int indexOf(K key) {
        int index = nodes.length;
        for (int i=0; i<nodes.length; i++) {
            if (nodes[i] != null && key.equals(nodes[i].k)) {
                index = i;
            }
        }
        return index;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    static class Node<K, V> {
        private K k;
        private V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }


    public static void main(String[] args) {
        HashMapV1<String, String> map = new HashMapV1<>();
        map.put("name", "chen");
        map.put("age", "18");
        System.out.println(map.get("name"));
        map.put("name", "prince");
        System.out.println(map.get("name"));
        map.put("sex", "male");
        System.out.println(map.size());
    }
}
