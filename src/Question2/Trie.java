/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author ssr7324
 */
public class Trie {

    private class TrieNode {

        private final Map<Character, TrieNode> children;
        private boolean isLeaf;
        private char element;

        public TrieNode() {
            children = new HashMap<>();
            isLeaf = false;
            element = '\u0000';
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean add(String element) {
        TrieNode currentNode = root;

        for (char character : element.toCharArray()) {
            currentNode = currentNode.getChildren().computeIfAbsent(character, c -> new TrieNode());
        }

        currentNode.setLeaf(true);
        return currentNode.isLeaf;
    }

    public boolean remove(String element) {
        TrieNode currentNode = root;

        for (char character : element.toCharArray()) {
            currentNode = currentNode.getChildren().computeIfAbsent(character, c -> null);
        }

        currentNode.setLeaf(false);
        return currentNode.isLeaf;
    }

    public boolean contains(String prefix) {
        TrieNode currentNode = root;

        for (char character : prefix.toCharArray()) {

            TrieNode next = currentNode.getChildren().get(character);

            if (Objects.isNull(next)) {
                return false;
            }

            currentNode = next;
        }

        return true;
    }

    public boolean removeAll(String prefix) {

        return true;
    }

    public boolean startsWith(String prefix) {
        if (contains(prefix) != false) {

            return true;
        } else {

            return false;
        }
    }

    public boolean suggestions(String prefix) {
        return true;
    }

    @Override
    public String toString() {
        return recursiveString(root, 0);
    }

    private String recursiveString(TrieNode current, int level) {
        String levelString = "";
        if (current.children.size() > 0) {
            Set<Character> chars = current.children.keySet();
            String tabs = "";
            for (int i = 0; i < level; i++) {
                tabs += "\t";
            }

            for (Character c : chars) {
                TrieNode child = current.children.get(c);
                levelString += tabs + " [" + c + "]";
                if (Objects.nonNull(child.element)) {
                    levelString += " >> " + child.element;
                }
                levelString += "\n";
                levelString += recursiveString(child, level + 1);
            }
        }
        return levelString;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("tea");
        trie.add("to");
        trie.add("him");
        trie.add("he");
        trie.add("hit");
        trie.add("her");

        System.out.println(trie);
    }
}
