package ru.job4j.io;

import java.io.*;

public class SaveToFile {

    private final File file;

    public SaveToFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < content.length(); i++) {
                bw.write(content.charAt(i));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
