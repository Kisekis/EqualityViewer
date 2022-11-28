package com.nime.eqviewer.rest;

import com.nime.eqviewer.api.CodesApi;
import com.nime.eqviewer.dto.SourceCodePairDto;
import com.nime.eqviewer.mapper.SourceCodePairMapper;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.service.SourceCodePairService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SourceCodePairController implements CodesApi {
    private final SourceCodePairMapper sourceCodePairMapper;
    private final SourceCodePairService sourceCodePairService;

    public SourceCodePairController(SourceCodePairMapper sourceCodePairMapper, SourceCodePairService sourceCodePairService) {
        this.sourceCodePairMapper = sourceCodePairMapper;
        this.sourceCodePairService = sourceCodePairService;
    }

    @Override
    public ResponseEntity<List<SourceCodePairDto>> listSourceCodePairs() {
        List<SourceCodePairDto> data = new ArrayList<>(sourceCodePairMapper.toSourceCodePairsDto(this.sourceCodePairService.sourceCodePairs()));
        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SourceCodePairDto> updateSourceCodePairs(@PathVariable("codesId") Integer codesId, @RequestBody SourceCodePairDto sourceCodePairDto) {
        List<SourceCodePair> data = this.sourceCodePairService.sourceCodePairs();
        SourceCodePairDto ret = null;
        for(SourceCodePair pair : data) {
            if(pair.id == codesId) {
                sourceCodePairService.changeResult(pair,
                        sourceCodePairMapper.toSourceCodePair(sourceCodePairDto).result);
                sourceCodePairService.changeLevel(pair,
                        sourceCodePairMapper.toSourceCodePair(sourceCodePairDto).level);
                ret = sourceCodePairMapper.toSourceCodePairDto(pair);
                break;
            }
        }
        if(ret == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
