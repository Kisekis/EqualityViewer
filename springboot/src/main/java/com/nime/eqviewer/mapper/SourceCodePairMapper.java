package com.nime.eqviewer.mapper;

import com.nime.eqviewer.dto.SourceCodePairDto;
import com.nime.eqviewer.dto.UnionFindDto;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.util.UnionFind;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface SourceCodePairMapper {
    Collection<SourceCodePairDto> toSourceCodePairsDto(Collection<SourceCodePair> sourceCodePairs);

    Collection<SourceCodePair> toSourceCodePairs(Collection<SourceCodePairDto> sourceCodePairs);

    SourceCodePair toSourceCodePair(SourceCodePairDto sourceCodePairDto);

    SourceCodePairDto toSourceCodePairDto(SourceCodePair sourceCodePair);

    UnionFindDto toUnionFindDto(UnionFind uf);
    UnionFind toUnionFind(UnionFindDto uf);
}
