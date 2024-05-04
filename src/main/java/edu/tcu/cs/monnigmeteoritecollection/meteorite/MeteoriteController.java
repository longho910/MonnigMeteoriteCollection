package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteDtoToMeteoriteConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteToMeteoriteDtoConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.endpoint.base-url}/meteorites")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class MeteoriteController {
    private final MeteoriteService meteoriteService;
    private final MeteoriteToMeteoriteDtoConverter meteoriteToMeteoriteDtoConverter;
    private final MeteoriteDtoToMeteoriteConverter meteoriteDtoToMeteoriteConverter;


    public MeteoriteController(MeteoriteService meteoriteService, MeteoriteToMeteoriteDtoConverter meteoriteToMeteoriteDtoConverter, MeteoriteDtoToMeteoriteConverter meteoriteDtoToMeteoriteConverter) {
        this.meteoriteService = meteoriteService;
        this.meteoriteToMeteoriteDtoConverter = meteoriteToMeteoriteDtoConverter;
        this.meteoriteDtoToMeteoriteConverter = meteoriteDtoToMeteoriteConverter;
    }

    @GetMapping("/{meteoriteId}")
    public Result findMeteoriteById(@PathVariable Long meteoriteId) {

        Meteorite foundMeteorite = this.meteoriteService.findById(meteoriteId);
        MeteoriteDto meteoriteDto = this.meteoriteToMeteoriteDtoConverter.convert(foundMeteorite);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", meteoriteDto);
    }

    @GetMapping
    public Result findAllMeteorites(Pageable pageable) { // create page request object
        Page<Meteorite> meteoritePage = this.meteoriteService.findAll(pageable);

        Page<MeteoriteDto> meteoriteDtoPage = meteoritePage
                .map(this.meteoriteToMeteoriteDtoConverter::convert);

        return new Result(true, StatusCode.SUCCESS, "Find All Success", meteoriteDtoPage);
    }

    // UC-16
    @GetMapping("/onloan/{loanId}")
    public Result findAllMeteoritesOnLoan(@PathVariable String loanId) {
        List<Meteorite> foundMeteorites = this.meteoriteService.findAllOnLoan();
        List<MeteoriteDto> meteoriteDtoList = new ArrayList<>();
        
        for (Meteorite meteorite : foundMeteorites) {
            meteoriteDtoList.add(this.meteoriteToMeteoriteDtoConverter.convert(meteorite));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All On Loan Success", meteoriteDtoList);
    }

    // this endpoint handles creation of meteorites, which are expected to be initialized with NULL SampleHistory and Loan attributes
    @PostMapping
    public Result addMeteorite(@Valid @RequestBody MeteoriteDto meteoriteDto) {
        // convert artifactDto to artifact
        Meteorite newMeteorite = this.meteoriteDtoToMeteoriteConverter.convert(meteoriteDto);
        Meteorite savedMeteorite = this.meteoriteService.save(newMeteorite);

        MeteoriteDto savedMeteoriteDto = this.meteoriteToMeteoriteDtoConverter.convert(savedMeteorite);

        return new Result(true, StatusCode.SUCCESS, "Add Success", savedMeteoriteDto);
    }

    // UC-9, UC-4
    // updates any number of attributes of a meteorite (includes SampleHistory and Loan)
    // however, both SampleHistory and Loans are created on separate endpoints
    @PutMapping("/{meteoriteId}")
    public Result updateMeteorite(@PathVariable String meteoriteId, @Valid @RequestBody MeteoriteDto meteoriteDto) {
        // convert meteoriteDto to meteorite to use meteoriteService
        Meteorite update = this.meteoriteDtoToMeteoriteConverter.convert(meteoriteDto);
        Meteorite updated = this.meteoriteService.update(meteoriteId, update);

        MeteoriteDto updatedDto = this.meteoriteToMeteoriteDtoConverter.convert(updated);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedDto);
    }

    @DeleteMapping("/{meteoriteId}")
    public Result deleteMeteorite(@PathVariable String meteoriteId) {
        this.meteoriteService.delete(meteoriteId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @PostMapping("/{meteoriteId}/sub")
    public Result createSubsample(@PathVariable String meteoriteId, @Valid @RequestBody MeteoriteDto meteoriteDto) {
        Meteorite newMeteorite = this.meteoriteDtoToMeteoriteConverter.convert(meteoriteDto);
        Meteorite savedMeteorite = this.meteoriteService.saveSub(meteoriteId, newMeteorite);

        MeteoriteDto savedMeteoriteDto = this.meteoriteToMeteoriteDtoConverter.convert(savedMeteorite);

        return new Result(true, StatusCode.SUCCESS, "Add Sub Success", savedMeteoriteDto);
    }

    @PostMapping("/search")
    public Result findMeteoritesByCriteria(@RequestBody Map<String, String> searchCriteria, Pageable pageable) {
        Page<Meteorite> meteoritePage = this.meteoriteService.findByCriteria(searchCriteria, pageable);
        Page<MeteoriteDto> meteoriteDtoPage = meteoritePage.map(this.meteoriteToMeteoriteDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Search Success", meteoriteDtoPage);
    }

}














