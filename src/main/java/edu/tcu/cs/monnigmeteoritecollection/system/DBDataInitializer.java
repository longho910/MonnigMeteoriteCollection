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
        meteorite1.set_group("H");
        meteorite1.setYearFound(1951);
        meteorite1.setWeight(BigDecimal.valueOf(325.1));
        meteorite1.setHowFound("Fall");

//        Abee	M499.2	Canada	Enstatite Chondrite	EH	1952	785.9

        Meteorite meteorite2 = new Meteorite();
        meteorite2.setId(2L);
        meteorite2.setName("Abee");
        meteorite2.setMonnigNumber("M499.2");
        meteorite2.setCountry("Canada");
        meteorite2.set_class("Enstatite Chondrite");
        meteorite2.set_group("EH");
        meteorite2.setYearFound(1952);
        meteorite2.setWeight(BigDecimal.valueOf(785.9));
        meteorite2.setHowFound("Find");


        Meteorite meteorite3 = new Meteorite();
        meteorite3.setId(3L);
        meteorite3.setName("Abernathy");
        meteorite3.setMonnigNumber("M239.1");
        meteorite3.setCountry("USA");
        meteorite3.set_class("Ordinary Chondrite");
        meteorite3.set_group("L");
        meteorite3.setYearFound(1941);
        meteorite3.setWeight(BigDecimal.valueOf(453.1));
        meteorite3.setHowFound("Fall");


        Meteorite meteorite4 = new Meteorite();
        meteorite4.setId(4L);
        meteorite4.setName("Abu Moharek");
        meteorite4.setMonnigNumber("M787.1");
        meteorite4.setCountry("Egypt");
        meteorite4.set_class("Ordinary Chondrite");
        meteorite4.set_group("H");
        meteorite4.setYearFound(1997);
        meteorite4.setWeight(BigDecimal.valueOf(56.4));
        meteorite4.setHowFound("Fall");

        //        Acapulco	M901.1	Mexico	Primitive Achondrite	Acapulcoite	1976	0.4
        Meteorite meteorite5 = new Meteorite();
        meteorite5.setId(5L);
        meteorite5.setName("Acapulco");
        meteorite5.setMonnigNumber("M901.1");
        meteorite5.setCountry("Mexico");
        meteorite5.set_class("Primitive Achondrite");
        meteorite5.set_group("Acapulcoite");
        meteorite5.setYearFound(1976);
        meteorite5.setWeight(BigDecimal.valueOf(0.4));
        meteorite5.setHowFound("Find");

        meteoriteRepository.save(meteorite1);
        meteoriteRepository.save(meteorite2);
        meteoriteRepository.save(meteorite3);
        meteoriteRepository.save(meteorite4);
        meteoriteRepository.save(meteorite5);
    }
}
