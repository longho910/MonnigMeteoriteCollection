package edu.tcu.cs.monnigmeteoritecollection.system;

import edu.tcu.cs.monnigmeteoritecollection.meteorite.Meteorite;
import edu.tcu.cs.monnigmeteoritecollection.meteorite.MeteoriteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DBDataInitializer implements CommandLineRunner {
    private final MeteoriteRepository meteoriteRepository;
    public DBDataInitializer(MeteoriteRepository meteoriteRepository) {
        this.meteoriteRepository = meteoriteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Name	Monnig Number	Country	Class	Group	Year Found	Sample Weight (g)
        // Abbott	M398.1	USA	Ordinary Chondrite	H	1951	325.1
        Meteorite meteorite1 = new Meteorite();
        meteorite1.setId(1L);
        meteorite1.setName("Abbott");
        meteorite1.setMonnigNumber("M398.1");
        meteorite1.setCountry("USA");
        meteorite1.set_class("Ordinary Chondrite");
        meteorite1.setGroup("H");
        meteorite1.setYearFound(1951);
        meteorite1.setWeight(BigDecimal.valueOf(325.1));

        meteoriteRepository.save(meteorite1);
    }
}
