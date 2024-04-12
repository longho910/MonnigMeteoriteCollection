package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import org.springframework.web.bind.annotation.*;

import edu.tcu.cs.monnigmeteoritecollection.system.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("${api.endpoint.base-url}/meteorites?searchTextFromUser")
public class MeteoriteController {

    final MeteoriteService meteoriteService = new MeteoriteService(null);

    @GetMapping
    public Result findAllMeteorites() {
        
        List<Meteorite> meteoriteList = this.meteoriteService.findAll();
        
        return new Result(true, StatusCode.SUCCESS, "Find All Success", meteoriteList);
    }
    
}
