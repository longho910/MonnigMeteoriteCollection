package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteDtoToMeteoriteConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteToMeteoriteDtoConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/meteorites")
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
    public Result findAllMeteorites() {
        List<Meteorite> foundMeteorites = this.meteoriteService.findAll();

        List<MeteoriteDto> meteoriteDtoList = new ArrayList<>();
        for (Meteorite meteorite : foundMeteorites) {
            meteoriteDtoList.add(this.meteoriteToMeteoriteDtoConverter.convert(meteorite));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All Success", meteoriteDtoList);
    }

    // use case 16
    @GetMapping
    public Result findAllMeteoritesOnLoan() {
        List<Meteorite> foundMeteorites = this.meteoriteService.findAllOnLoan();

        List<MeteoriteDto> meteoriteDtoList = new ArrayList<>();
        for (Meteorite meteorite : foundMeteorites) {
            meteoriteDtoList.add(this.meteoriteToMeteoriteDtoConverter.convert(meteorite));
        }

        return new Result(true, StatusCode.SUCCESS, "Find All On Loan Success", meteoriteDtoList);
    }


    @PostMapping
    public Result addMeteorite(@Valid @RequestBody MeteoriteDto meteoriteDto) {
        // convert artifactDto to artifact
        Meteorite newMeteorite = this.meteoriteDtoToMeteoriteConverter.convert(meteoriteDto);
        Meteorite savedMeteorite = this.meteoriteService.save(newMeteorite);

        MeteoriteDto savedMeteoriteDto = this.meteoriteToMeteoriteDtoConverter.convert(savedMeteorite);

        return new Result(true, StatusCode.SUCCESS, "Add Success", savedMeteoriteDto);
    }

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

}














