/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question2;

/**
 *
 * @author ssr7324
 */
public class Trie {

    TrieNode root;

    public Trie() {
        root = new Trie();
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
