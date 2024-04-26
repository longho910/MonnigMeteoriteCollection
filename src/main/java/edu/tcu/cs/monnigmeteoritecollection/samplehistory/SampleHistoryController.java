package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter.SampleHistoryDtoToSampleHistoryConverter;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.converter.SampleHistoryToSampleHistoryDtoConverter;
import edu.tcu.cs.monnigmeteoritecollection.samplehistory.dto.SampleHistoryDto;
import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.endpoint.base-url}/history")
public class SampleHistoryController {
    private final SampleHistoryService sampleHistoryService;
    private final SampleHistoryDtoToSampleHistoryConverter sampleHistoryDtoToSampleHistoryConverter;
    private final SampleHistoryToSampleHistoryDtoConverter sampleHistoryToSampleHistoryDtoConverter;

    public SampleHistoryController(SampleHistoryService sampleHistoryService, SampleHistoryDtoToSampleHistoryConverter sampleHistoryDtoToSampleHistoryConverter, SampleHistoryToSampleHistoryDtoConverter sampleHistoryToSampleHistoryDtoConverter) {
        this.sampleHistoryService = sampleHistoryService;
        this.sampleHistoryDtoToSampleHistoryConverter = sampleHistoryDtoToSampleHistoryConverter;
        this.sampleHistoryToSampleHistoryDtoConverter = sampleHistoryToSampleHistoryDtoConverter;
    }

    @GetMapping("/{historyId}")
    public Result findHistoryById(@PathVariable Integer historyId) {
        SampleHistory foundHistory = this.sampleHistoryService.findById(historyId);
        SampleHistoryDto historyDto = this.sampleHistoryToSampleHistoryDtoConverter.convert(foundHistory);

        return new Result(true, StatusCode.SUCCESS, "Find One Success", historyDto);


    }


}
