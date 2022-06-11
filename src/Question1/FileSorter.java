/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JProgressBar;
import java.util.Objects;

/**
 *
 * @author ssr7324
 * @param <E>
 */
public class FileSorter<E> {

    private int limit;
    private JProgressBar splitJProgressBar;
    private JProgressBar mergeJProgressBar;
    public int splitProgress;
    public int mergeProgress;
    private LinkedQueue<File> inputTextFiles;
    private LinkedQueue<File> outputTextFiles;

    public FileSorter(int limit) {
        this.limit = limit;
        this.inputTextFiles = new LinkedQueue<>();
        this.outputTextFiles = new LinkedQueue<>();
    }

    public FileSorter() {
        this.limit = 0;
        this.inputTextFiles = new LinkedQueue<>();
        this.outputTextFiles = new LinkedQueue<>();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LinkedQueue<File> split(File inputFile, JProgressBar splitJProgressBar) throws FileNotFoundException, IOException {
        LinkedQueue<File> tempFiles;

        try (BufferedReader inputStream = new BufferedReader(new FileReader(inputFile))) {
            String line = inputStream.readLine();
            tempFiles = new LinkedQueue<>();

            this.splitProgress = 0;

            int numOfFile = 0;

            while (Objects.nonNull(line)) {
                ArrayList<String> temp = new ArrayList<>();
                File tempFile = new File("Temp" + numOfFile + ".txt");
                try (PrintWriter outputStream = new PrintWriter(new FileWriter(tempFile))) {
                    for (int i = 0; i < limit; i++) {
                        temp.add(line);
                        line = inputStream.readLine();
                    }

                    Collections.sort(temp);

                    for (String str : temp) {
                        if (Objects.nonNull(str)) {
                            outputStream.write(str);
                            outputStream.write("\r\n");
                        }
                    }
                }

                tempFiles.enqueue(tempFile);
                numOfFile++;

                splitProgress += 10;

                this.splitJProgressBar.setValue(splitProgress);

                System.out.println(splitProgress);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            inputStream.close();
        }

        return tempFiles;
    }

    public void merge(JProgressBar merProgressBar) throws IOException {
        this.mergeProgress = 0;
        File input = inputTextFiles.dequeue();
        File output = outputTextFiles.dequeue();

        LinkedQueue<File> splitted = this.split(input, splitJProgressBar);

        int numOfFile = 0;

        while (splitted.size() > 0) {
            File firstFile = splitted.dequeue();
            File anotherFile = splitted.dequeue();

            BufferedReader firstInputStream = new BufferedReader(new FileReader(firstFile));
            BufferedReader anotherInputStream = new BufferedReader(new FileReader(anotherFile));

            String firstStr = firstInputStream.readLine();
            String anotherStr = anotherInputStream.readLine();

            File tempFile = new File("Temp" + numOfFile + ".txt");
            PrintWriter outputStream = new PrintWriter(new FileWriter(tempFile));
            ArrayList<String> tempCalledLeft = new ArrayList<>();
            ArrayList<String> tempCalledRight = new ArrayList<>();
            ArrayList<String> tempCalledWhole = new ArrayList<>();

            while (Objects.isNull(firstStr)) {
                tempCalledLeft.add(firstStr);
                firstStr = firstInputStream.readLine();
                tempCalledWhole.add(firstStr);
            }
            firstInputStream.close();
            firstFile.delete();

            while (Objects.isNull(anotherStr)) {
                tempCalledRight.add(anotherStr);
                anotherStr = anotherInputStream.readLine();
                tempCalledWhole.add(anotherStr);
            }

            anotherInputStream.close();
            anotherFile.delete();

            mergeArray(tempCalledLeft, tempCalledRight, tempCalledWhole);

            for (String str : tempCalledWhole) {
                if (Objects.nonNull(str));
                outputStream.write(str);
            }

            splitted.enqueue(tempFile);
            outputStream.close();

            mergeProgress += 10;
            mergeJProgressBar.setValue(mergeProgress);

            System.out.println(mergeProgress);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void mergeArray(ArrayList<String> leftArrayList, ArrayList<String> rightArrayList, ArrayList<String> wholeArrayList) {
        int left = 0;
        int right = 0;
        int whole = 0;

        while (left < leftArrayList.size()) {
            while (right < rightArrayList.size()) {
                if (leftArrayList.get(left).compareTo(rightArrayList.get(right)) < 0) {
                    wholeArrayList.set(whole, leftArrayList.get(left));
                    left++;
                } else {
                    wholeArrayList.set(whole, rightArrayList.get(right));
                    right++;
                }
                whole++;
            }
        }

        ArrayList<String> arr = new ArrayList<>();

        int i = 0;

        if (left >= leftArrayList.size()) {
            arr = rightArrayList;
            i = right;
        } else {
            arr = leftArrayList;
            i = left;
        }

        for (int j = 0; j < arr.size(); j++) {
            wholeArrayList.set(whole, arr.get(j));
            whole++;
        }
    }
}
