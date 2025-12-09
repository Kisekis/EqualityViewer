package com.nime.eqviewer.util;

import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.security.InputValidator;

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
            FileWriter uncertainWriter = new FileWriter(folderPath + "uncertain.csv");
            sameWriter.write("file1,file2,\n");
            equalWriter.write("file1,file2,\n");
            inequalWriter.write("file1,file2,\n");
            uncertainWriter.write("file1,file2,\n");
            for(SourceCodePair s : data) {
                // Sanitize the CSV content to prevent CSV injection
                String sanitizedOutput = sanitizeForCsv(s);
                switch (s.result) {
                    case SAME:
                        sameWriter.write(sanitizedOutput);
                        break;
                    case EQUAL:
                        equalWriter.write(sanitizedOutput);
                        break;
                    case INEQUAL:
                        inequalWriter.write(sanitizedOutput);
                        break;
                    case UNCERTAIN:
                        uncertainWriter.write(sanitizedOutput);
                        break;
                }
            }
            sameWriter.close();
            equalWriter.close();
            inequalWriter.close();
            uncertainWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sanitize SourceCodePair data for CSV output to prevent CSV injection
     */
    private String sanitizeForCsv(SourceCodePair pair) {
        String path1 = InputValidator.sanitizeCsvContent(pair.code1.path);
        String path2 = InputValidator.sanitizeCsvContent(pair.code2.path);
        return path1 + "," + path2 + ",\n";
    }

}
