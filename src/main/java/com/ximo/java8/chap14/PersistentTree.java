package com.ximo.java8.chap14;

/**
 * @author xikl
 * @date 2018/12/12
 */
public class PersistentTree {

    static class Tree {
        private String key;

        private int value;

        private Tree left, right;

        public Tree(String key, int value, Tree left, Tree right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    public static int find(String k, int defaultValue, Tree tree) {
        if (tree == null) {
            return defaultValue;
        }

        if (tree.key.equals(k)) {
            return tree.value;
        }

        if (k.compareTo(tree.key) > 0) {
            return find(k, defaultValue, tree.right);
        } else {
            return find(k, defaultValue, tree.left);
        }

    }

    public static Tree set(String key, int newVal, Tree tree) {
        if (tree == null) {
            return new Tree(key, newVal, null, null);
        }
        if (key.equals(tree.key)) {
            tree.value = newVal;
            return tree;
        }

        if (tree.key.compareTo(key) > 0) {
            tree.right = set(key, newVal, tree.right);
        } else {
            tree.left = set(key, newVal, tree.left);
        }

        return tree;
    }

    public static Tree setByFunction(String key, int newVal, Tree tree) {
        if (tree == null) {
            return new Tree(key, newVal, null, null);
        }

        if (key.equals(tree.key)) {
            return new Tree(key, newVal, tree.left, tree.right);
        }

        if (tree.key.compareTo(key) > 0) {
            return new Tree(tree.key, tree.value, tree.left, setByFunction(key, newVal, tree.right));
        } else {
            return new Tree(tree.key, tree.value, setByFunction(key, newVal, tree.left), tree.right);
        }



    }



}
