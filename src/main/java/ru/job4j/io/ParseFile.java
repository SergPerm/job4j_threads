package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        return getContents(data -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        return getContents(data -> data < 0x80);
    }

    private synchronized String getContents(Predicate<Integer> predicate) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = br.read()) > 0) {
                if (predicate.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return output.toString();
    }
}