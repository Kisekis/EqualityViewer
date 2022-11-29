package com.nime.eqviewer.model;

public class SourceCodePair {
    public SourceCode code1;
    public SourceCode code2;
    public ResultType result;

    public Confilevel level;

    public int id;
    public SourceCodePair(SourceCode code1,SourceCode code2,ResultType result,int id) {
        this.code1 = code1;
        this.code2 = code2;
        this.result = result;
        level = result == ResultType.EQUAL ?Confilevel.UNRELIABLE :Confilevel.RELIABLE;
        this.id = id;
    }

    @Override
    public String toString() {
        return code1+","+code2+",\n";
    }
}
