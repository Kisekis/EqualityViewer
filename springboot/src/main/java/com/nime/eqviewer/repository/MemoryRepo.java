package com.nime.eqviewer.repository;

import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.util.CSVReader;

import java.util.HashMap;
import java.util.List;

public class MemoryRepo {
    private final static MemoryRepo instance = new MemoryRepo();
    private List<SourceCodePair> data;
    private HashMap<String, SourceCode>map;
    private MemoryRepo() {
        CSVReader reader = new CSVReader("./springboot/src/main/resources/");
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
