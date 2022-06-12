/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ssr7324
 */
public class Trie {

    private class TrieNode {

        private final Map<String, TrieNode> children = new HashMap<>();
        private boolean isLeaf;

        public Map<String, TrieNode> getChildren() {
            return children;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
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
                if (child.element != null) {
                    levelString += " >> " + child.element;
                }
                levelString += "\n";
                levelString += recursiveString(child, level + 1);
            }
        }
        return levelString;
    }

}
