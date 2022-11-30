package com.nime.eqviewer.rest;

import com.nime.eqviewer.api.CodeApi;
import com.nime.eqviewer.api.CodesApi;
import com.nime.eqviewer.api.UnionfindApi;
import com.nime.eqviewer.api.WriteApi;
import com.nime.eqviewer.dto.SourceCodePairDto;
import com.nime.eqviewer.dto.UnionFindDto;
import com.nime.eqviewer.mapper.SourceCodePairMapper;
import com.nime.eqviewer.model.SourceCode;
import com.nime.eqviewer.model.SourceCodePair;
import com.nime.eqviewer.repository.MemoryRepo;
import com.nime.eqviewer.service.SourceCodePairService;
import com.nime.eqviewer.util.CSVWriter;
import com.nime.eqviewer.util.UnionFind;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/api")
public class SourceCodePairController implements  CodesApi, CodeApi, UnionfindApi, WriteApi {
    private final SourceCodePairMapper sourceCodePairMapper;
    private final SourceCodePairService sourceCodePairService;


    public SourceCodePairController(SourceCodePairMapper sourceCodePairMapper, SourceCodePairService sourceCodePairService) {
        this.sourceCodePairMapper = sourceCodePairMapper;
        this.sourceCodePairService = sourceCodePairService;
    }

    @Override
    public ResponseEntity<UnionFindDto> getUnionFind() {
        UnionFind uf = sourceCodePairService.checkSourceCodePairs();
        return new ResponseEntity<>(sourceCodePairMapper.toUnionFindDto(uf), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SourceCodePairDto>> listSourceCodePairs() {
        sourceCodePairService.checkSourceCodePairs();
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

    @Override
    public ResponseEntity<String> getSourceCode(Integer codeID) {
        String ret = null;
        for(SourceCode s : MemoryRepo.getInstance().getMap().values()) {
            if(s.id == codeID) {
                ret = sourceCodePairService.getSourceCode(s);
            }
        }
        if(ret == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<SourceCodePairDto> getSourceCodePair(@PathVariable("codesId") Integer codesId) {
        List<SourceCodePair> data = this.sourceCodePairService.sourceCodePairs();
        SourceCodePairDto ret = null;
        for(SourceCodePair pair : data) {
            if(pair.id == codesId) {
                ret = sourceCodePairMapper.toSourceCodePairDto(pair);
                break;
            }
        }
        if(ret == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> writeSourceCodePairs() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        CSVWriter writer = new CSVWriter(resourceBundle.getString("path"));
        try{
            writer.write(this.sourceCodePairService.sourceCodePairs());
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CodesApi.super.getRequest();
    }
}
