package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MeteoriteService {

    private final MeteoriteRepository meteoriteRepository;

    public MeteoriteService(MeteoriteRepository meteoriteRepository) {
        this.meteoriteRepository = meteoriteRepository;
    }

    public List<Meteorite> findAll() {

        return this.meteoriteRepository.findAll();

    }
}
