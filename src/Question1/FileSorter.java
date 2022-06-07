/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

import java.io.*;

/**
 *
 * @author ssr7324
 * @param <E>
 */

public class FileSorter<E>{
    private int limit; 
    private LinkedQueue<File> input;
    private LinkedQueue<File> output;
    
    public FileSorter(int limit){
        this.limit = limit;
        this.input = new LinkedQueue<>();
        this.output = new LinkedQueue<>();
    }
    
    public void setLimit(int limit){
        this.limit = limit;
    }
}
