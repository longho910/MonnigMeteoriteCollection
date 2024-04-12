package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import org.springframework.web.bind.annotation.*;

import edu.tcu.cs.monnigmeteoritecollection.system.*;

import java.util.List;


@RestController
@RequestMapping("${api.endpoint.base-url}/meteorites?searchTextFromUser")
public class MeteoriteController {

    private final MeteoriteService meteoriteService;

    public MeteoriteController(MeteoriteService meteoriteService) {
        this.meteoriteService = meteoriteService;
    }
    @GetMapping
    public Result findAllMeteorites() {
        
        List<Meteorite> meteoriteList = this.meteoriteService.findAll();
        
        return new Result(true, StatusCode.SUCCESS, "Find All Success", meteoriteList);
    }
    
}
