package com.nime.eqviewer.util;

import com.nime.eqviewer.model.ResultType;
import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVReader {
    private String folderPath;
    private List<SourceCodePair> data;
    private HashMap<String,SourceCode> map;

    private static int globalId = 0;
    private static int globalPairId = 0;
    public CSVReader(String path) {
        this.folderPath = path + "/input/csv/";
        data = new ArrayList<>();
        map = new HashMap<>();
    }
    public void read() {
        readSingleCSV("equal.csv",ResultType.EQUAL);
        readSingleCSV("inequal.csv",ResultType.INEQUAL);
        readSingleCSV("same.csv",ResultType.SAME);
    }
    public void readSingleCSV(String fileName, ResultType type) {
        try (BufferedReader br = new BufferedReader(new FileReader(folderPath+fileName))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(count!=0) {
                    String[] res = line.split(",");
                    String path1 = res[0];
                    String path2 = res[1];
                    if(!map.containsKey(path1)) {
                        map.put(path1,new SourceCode(path1,globalId));
                        globalId++;
                    }
                    if(!map.containsKey(path2)) {
                        map.put(path2,new SourceCode(path2,globalId));
                        globalId++;
                    }
                    data.add(new SourceCodePair(map.get(path1),map.get(path2),type,globalPairId));
                    globalPairId++;
                }
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SourceCodePair> getData() {
        return data;
    }

    public HashMap<String, SourceCode> getMap() {
        return map;
    }
}
