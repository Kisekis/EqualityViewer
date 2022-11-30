package com.nime.eqviewer.util;

import com.nime.eqviewer.model.SourceCodePair;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private String folderPath = "./";
    public CSVWriter(String outputPath) {
        this.folderPath = outputPath + "/output/";
    }
    public void write(List<SourceCodePair> data) {
        try {
            FileWriter sameWriter = new FileWriter(folderPath + "same.csv");
            FileWriter equalWriter = new FileWriter(folderPath + "equal.csv");
            FileWriter inequalWriter = new FileWriter(folderPath + "inequal.csv");
            sameWriter.write("file1,file2,\n");
            equalWriter.write("file1,file2,\n");
            inequalWriter.write("file1,file2,\n");
            for(SourceCodePair s : data) {
                switch (s.result) {
                    case SAME:
                        sameWriter.write(s.toString());
                        break;
                    case EQUAL:
                        equalWriter.write(s.toString());
                        break;
                    case INEQUAL:
                        inequalWriter.write(s.toString());
                        break;
                }
            }
            sameWriter.close();
            equalWriter.close();
            inequalWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
