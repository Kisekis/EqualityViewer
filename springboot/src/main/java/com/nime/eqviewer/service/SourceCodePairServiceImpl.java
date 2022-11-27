package com.nime.eqviewer.service;

import com.nime.eqviewer.model.Confilevel;
import com.nime.eqviewer.model.ResultType;
import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.repository.MemoryRepo;
import com.nime.eqviewer.util.UnionFind;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class SourceCodePairServiceImpl implements SourceCodePairService{
    @Override
    public List<SourceCodePair> sourceCodePairs() {
        return MemoryRepo.getInstance().getData();
    }

    @Override
    public void checkSourceCodePairs() {

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
                    pair.level = Confilevel.SUSPICIOUS;
                }
            }
        }
    }

    @Override
    public void changeResult(SourceCodePair pair, ResultType type) {
        pair.result = type;
    }
}
