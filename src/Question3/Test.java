/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question3;

/**
 *
 * @author ssr7324
 */
public class Test {

    public static void main(String[] args) {
        HashSetWithChaining hashSetWithChaining = new HashSetWithChaining();

        System.out.println("Creating Set, initial capacity = " + hashSetWithChaining.size() + ".. Adding Seth, Bob, Adam, Ian");

        hashSetWithChaining.add("Seth");
        hashSetWithChaining.add("Bob");
        hashSetWithChaining.add("Adam");
        hashSetWithChaining.add("Ian");

        System.out.println(hashSetWithChaining);

        hashSetWithChaining.remove(1, "Seth");

        System.out.println(hashSetWithChaining);

        System.out.println("Adding John, Jun and JY in Set");

        hashSetWithChaining.add("John");
        hashSetWithChaining.add("Jun");
        hashSetWithChaining.add("JY");

        System.out.println(hashSetWithChaining);

        hashSetWithChaining.remove(10, "John");

        System.out.println(hashSetWithChaining);
    }
}
