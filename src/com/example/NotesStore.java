package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {

    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = DATA_DIR + "/notes.csv";

    public NotesStore() {
        ensureDataFileExists();
    }

    // add
    public void add(String text) {
        List<String> notes = readAll();
        int nextId = notes.size() + 1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(nextId + ";" + text);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing note");
        }
    }

    // list
    public void list() {
        List<String> notes = readAll();

        if (notes.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        for (String note : notes) {
            System.out.println(note);
        }
    }

    // ---------- helpers ----------

    private List<String> readAll() {
        List<String> result = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return result;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading notes");
        }

        return result;
    }

    private void ensureDataFileExists() {
        try {
            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage");
        }
    }
}
