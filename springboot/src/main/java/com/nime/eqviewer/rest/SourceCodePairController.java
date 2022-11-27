package com.nime.eqviewer.rest;

import com.nime.eqviewer.api.CodesApi;
import com.nime.eqviewer.dto.SourceCodePairDto;
import com.nime.eqviewer.mapper.SourceCodePairMapper;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.service.SourceCodePairService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
