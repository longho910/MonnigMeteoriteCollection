package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.stat.internal.StatsHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tcu.cs.monnigmeteoritecollection.samplehistory.*;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter.*;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;

import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;

import jakarta.validation.Valid;

@RequestMapping("${api.endpoint.base-url}/histories")
public class SampleHistoryController {
    
    private final SampleHistoryDtoToSampleHistoryConverter sampleHistoryDtoToSampleHistoryConverter;
    private final SampleHistoryToSampleHistoryDtoConverter sampleHistoryToSampleHistoryDtoConverter;
    private final SampleHistoryService sampleHistoryService;


    public SampleHistoryController(SampleHistoryService sampleHistoryService, SampleHistoryToSampleHistoryDtoConverter sampleHistoryToSampleHistoryDtoConverter, SampleHistoryDtoToSampleHistoryConverter sampleHistoryDtoToSampleHistoryConverter) {
        this.sampleHistoryService = sampleHistoryService;
        this.sampleHistoryToSampleHistoryDtoConverter = sampleHistoryToSampleHistoryDtoConverter;
        this.sampleHistoryDtoToSampleHistoryConverter = sampleHistoryDtoToSampleHistoryConverter;
    }


    // C
    @PostMapping
    public Result addSampleHistory(@Valid @RequestBody SampleHistoryDto sampleHistoryDto) {
        // convert dto to Java object
        SampleHistory newHistory = this.sampleHistoryDtoToSampleHistoryConverter.convert(sampleHistoryDto);
        SampleHistory savedHistory = this.sampleHistoryService.save(newHistory);

        SampleHistoryDto savedHistoryDto = this.sampleHistoryToSampleHistoryDtoConverter.convert(savedHistory);

        return new Result(true, StatusCode.SUCCESS, "Add Success", savedHistoryDto);
    }

    // R - find all histories for a given meteorite
    @GetMapping("/mid/{meteoriteId}")
    public Result findAllHistoriesForMeteorite(@PathVariable String meteoriteId) {
        List<SampleHistory> foundHistories = this.sampleHistoryService.findAllHistoryForMeteorite(meteoriteId);

        List<SampleHistoryDto> historyDtoList = new ArrayList<>();
        for (SampleHistory elem : foundHistories) {
            historyDtoList.add(this.sampleHistoryToSampleHistoryDtoConverter.convert(elem));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All Histories For Meteorite Success", historyDtoList);        
    }

    // R - find a single history
    @GetMapping("/{historyId}")
    public Result findHistoryById(@PathVariable String historyId) {

        SampleHistory foundHistory = this.sampleHistoryService.findById(historyId);
        SampleHistoryDto historyDto = this.sampleHistoryToSampleHistoryDtoConverter.convert(foundHistory);

        return new Result(true, StatusCode.SUCCESS, "Find One Success", historyDto);
    }

    // U - update a single history
    @PutMapping("/{historyId}")
    public Result updateHistory(@PathVariable String historyId, @Valid @RequestBody SampleHistoryDto historyDto) {
        // convert DTO to Java object
        SampleHistory update = this.sampleHistoryDtoToSampleHistoryConverter.convert(historyDto);
        SampleHistory updated = this.sampleHistoryService.update(historyId, update);

        SampleHistoryDto updatedHistoryDto = this.sampleHistoryToSampleHistoryDtoConverter.convert(updated);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedHistoryDto);
    }

    // D - UC-10, resets SampleHistory attribute to NULL
    @DeleteMapping("/{historyId}")
    public Result deleteSampleHistory(@PathVariable String historyId) {
        this.sampleHistoryService.deleteSampleHistory(historyId);
        return new Result(true, StatusCode.SUCCESS, "Delete Sample History Success");
    }

}
