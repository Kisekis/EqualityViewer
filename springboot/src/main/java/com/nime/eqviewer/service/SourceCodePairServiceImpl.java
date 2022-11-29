package com.nime.eqviewer.service;

import com.nime.eqviewer.model.Confilevel;
import com.nime.eqviewer.model.ResultType;
import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.repository.MemoryRepo;
import com.nime.eqviewer.util.UnionFind;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


@Service
public class SourceCodePairServiceImpl implements SourceCodePairService{
    @Override
    public List<SourceCodePair> sourceCodePairs() {
        return MemoryRepo.getInstance().getData();
    }

    @Override
    public UnionFind checkSourceCodePairs() {
        List<SourceCodePair> data = MemoryRepo.getInstance().getData();
        HashMap<String, SourceCode> map = MemoryRepo.getInstance().getMap();
        UnionFind uf = new UnionFind(map.size());
        for(SourceCodePair pair : data) {
            if(pair.result == ResultType.SAME || pair.result == ResultType.EQUAL) {
                uf.union(pair.code1.id,pair.code2.id);
            }
        }
        for(SourceCodePair pair : data) {
            if (pair.result == ResultType.INEQUAL) {
                if (uf.connected(pair.code1.id, pair.code2.id)) {
                    for(SourceCodePair pairr : data) {
                        if(uf.connected(pairr.code1.id, pair.code1.id) && uf.connected(pairr.code2.id, pair.code1.id) && pairr.result == ResultType.EQUAL &&pairr.level != Confilevel.RELIABLE) {
                            pairr.level = Confilevel.SUSPICIOUS;
                        }
                    }
                }
            }
        }
        return uf;
    }

    @Override
    public void changeResult(SourceCodePair pair, ResultType type) {
        pair.result = type;
    }
    @Override
    public void changeLevel(SourceCodePair pair, Confilevel level) {
        pair.level = level;
    }

    @Override
    public String getSourceCode(SourceCode code) {
        String path = code.path;
        String content = null;
        try{
            content = Files.readString(Path.of(path), Charset.defaultCharset());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
