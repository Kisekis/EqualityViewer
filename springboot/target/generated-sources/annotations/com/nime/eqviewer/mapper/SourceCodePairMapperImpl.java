package com.nime.eqviewer.mapper;

import com.nime.eqviewer.dto.ConfilevelDto;
import com.nime.eqviewer.dto.ResultTypeDto;
import com.nime.eqviewer.dto.SourceCodeDto;
import com.nime.eqviewer.dto.SourceCodePairDto;
import com.nime.eqviewer.model.Confilevel;
import com.nime.eqviewer.model.ResultType;
import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class SourceCodePairMapperImpl implements SourceCodePairMapper {

    @Override
    public Collection<SourceCodePairDto> toSourceCodePairsDto(Collection<SourceCodePair> sourceCodePairs) {
        if ( sourceCodePairs == null ) {
            return null;
        }

        Collection<SourceCodePairDto> collection = new ArrayList<SourceCodePairDto>( sourceCodePairs.size() );
        for ( SourceCodePair sourceCodePair : sourceCodePairs ) {
            collection.add( toSourceCodePairDto( sourceCodePair ) );
        }

        return collection;
    }

    @Override
    public Collection<SourceCodePair> toSourceCodePairs(Collection<SourceCodePairDto> sourceCodePairs) {
        if ( sourceCodePairs == null ) {
            return null;
        }

        Collection<SourceCodePair> collection = new ArrayList<SourceCodePair>( sourceCodePairs.size() );
        for ( SourceCodePairDto sourceCodePairDto : sourceCodePairs ) {
            collection.add( toSourceCodePair( sourceCodePairDto ) );
        }

        return collection;
    }

    @Override
    public SourceCodePair toSourceCodePair(SourceCodePairDto sourceCodePairDto) {
        if ( sourceCodePairDto == null ) {
            return null;
        }

        SourceCode code1 = null;
        SourceCode code2 = null;
        ResultType result = null;
        int id = 0;

        code1 = sourceCodeDtoToSourceCode( sourceCodePairDto.getCode1() );
        code2 = sourceCodeDtoToSourceCode( sourceCodePairDto.getCode2() );
        result = resultTypeDtoToResultType( sourceCodePairDto.getResult() );
        if ( sourceCodePairDto.getId() != null ) {
            id = sourceCodePairDto.getId();
        }

        SourceCodePair sourceCodePair = new SourceCodePair( code1, code2, result, id );

        sourceCodePair.level = confilevelDtoToConfilevel( sourceCodePairDto.getLevel() );

        return sourceCodePair;
    }

    @Override
    public SourceCodePairDto toSourceCodePairDto(SourceCodePair sourceCodePair) {
        if ( sourceCodePair == null ) {
            return null;
        }

        SourceCodePairDto sourceCodePairDto = new SourceCodePairDto();

        sourceCodePairDto.setCode1( sourceCodeToSourceCodeDto( sourceCodePair.code1 ) );
        sourceCodePairDto.setCode2( sourceCodeToSourceCodeDto( sourceCodePair.code2 ) );
        sourceCodePairDto.setResult( resultTypeToResultTypeDto( sourceCodePair.result ) );
        sourceCodePairDto.setLevel( confilevelToConfilevelDto( sourceCodePair.level ) );
        sourceCodePairDto.setId( sourceCodePair.id );

        return sourceCodePairDto;
    }

    protected SourceCode sourceCodeDtoToSourceCode(SourceCodeDto sourceCodeDto) {
        if ( sourceCodeDto == null ) {
            return null;
        }

        String path = null;
        int id = 0;

        path = sourceCodeDto.getPath();
        if ( sourceCodeDto.getId() != null ) {
            id = sourceCodeDto.getId();
        }

        SourceCode sourceCode = new SourceCode( path, id );

        return sourceCode;
    }

    protected ResultType resultTypeDtoToResultType(ResultTypeDto resultTypeDto) {
        if ( resultTypeDto == null ) {
            return null;
        }

        ResultType resultType;

        switch ( resultTypeDto ) {
            case SAME: resultType = ResultType.SAME;
            break;
            case EQUAL: resultType = ResultType.EQUAL;
            break;
            case INEQUAL: resultType = ResultType.INEQUAL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + resultTypeDto );
        }

        return resultType;
    }

    protected Confilevel confilevelDtoToConfilevel(ConfilevelDto confilevelDto) {
        if ( confilevelDto == null ) {
            return null;
        }

        Confilevel confilevel;

        switch ( confilevelDto ) {
            case SUSPICIOUS: confilevel = Confilevel.SUSPICIOUS;
            break;
            case RELIABLE: confilevel = Confilevel.RELIABLE;
            break;
            case UNRELIABLE: confilevel = Confilevel.UNRELIABLE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + confilevelDto );
        }

        return confilevel;
    }

    protected SourceCodeDto sourceCodeToSourceCodeDto(SourceCode sourceCode) {
        if ( sourceCode == null ) {
            return null;
        }

        SourceCodeDto sourceCodeDto = new SourceCodeDto();

        sourceCodeDto.setPath( sourceCode.path );
        sourceCodeDto.setId( sourceCode.id );

        return sourceCodeDto;
    }

    protected ResultTypeDto resultTypeToResultTypeDto(ResultType resultType) {
        if ( resultType == null ) {
            return null;
        }

        ResultTypeDto resultTypeDto;

        switch ( resultType ) {
            case SAME: resultTypeDto = ResultTypeDto.SAME;
            break;
            case EQUAL: resultTypeDto = ResultTypeDto.EQUAL;
            break;
            case INEQUAL: resultTypeDto = ResultTypeDto.INEQUAL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + resultType );
        }

        return resultTypeDto;
    }

    protected ConfilevelDto confilevelToConfilevelDto(Confilevel confilevel) {
        if ( confilevel == null ) {
            return null;
        }

        ConfilevelDto confilevelDto;

        switch ( confilevel ) {
            case SUSPICIOUS: confilevelDto = ConfilevelDto.SUSPICIOUS;
            break;
            case RELIABLE: confilevelDto = ConfilevelDto.RELIABLE;
            break;
            case UNRELIABLE: confilevelDto = ConfilevelDto.UNRELIABLE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + confilevel );
        }

        return confilevelDto;
    }
}
