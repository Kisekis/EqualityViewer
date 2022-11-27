package com.nime.eqviewer.service;

import com.nime.eqviewer.model.ResultType;
import com.nime.eqviewer.model.SourceCodePair;

import java.util.List;

public interface SourceCodePairService {
    public List<SourceCodePair> sourceCodePairs();

    public void checkSourceCodePairs();

    public void changeResult(SourceCodePair pair, ResultType type);
}
