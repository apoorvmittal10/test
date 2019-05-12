package com.test.thread.queue.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class FilePersistMessageImpl implements IPersistMessage {

    private FileWriter fileWriter;

    public FilePersistMessageImpl() {
        try {
            fileWriter = new FileWriter("testData", true);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO handle exception
        }
    }

    @Override
    public boolean persistData(Serializable message) {
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(message);
        printWriter.close();
        return true;
    }
}
