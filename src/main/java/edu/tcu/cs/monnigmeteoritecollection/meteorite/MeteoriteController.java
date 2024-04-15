package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteDtoToMeteoriteConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.converter.MeteoriteToMeteoriteDtoConverter;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.dto.MeteoriteDto;
import edu.tcu.cs.monnigmeteoritecollection.system.Result;
import edu.tcu.cs.monnigmeteoritecollection.system.StatusCode;
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
}














