package com.nime.eqviewer.repository;

import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.util.CSVReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class MemoryRepo {
    private final static MemoryRepo instance = new MemoryRepo();
    private List<SourceCodePair> data;
    private HashMap<String, SourceCode>map;
    private String path;
    private MemoryRepo() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        CSVReader reader = new CSVReader(resourceBundle.getString("path"));
        reader.read();
        data = reader.getData();
        map = reader.getMap();
    }
    public static MemoryRepo getInstance() {
        return instance;
    }


    public List<SourceCodePair> getData() {
        return data;
    }

    public HashMap<String, SourceCode>  getMap() {
        return map;
    }
}
