package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MeteoriteService {
    private final MeteoriteRepository meteoriteRepository;

    public MeteoriteService(MeteoriteRepository meteoriteRepository) {
        this.meteoriteRepository = meteoriteRepository;
    }

    public Meteorite findById(Long meteoriteId) {
        return this.meteoriteRepository.findById(meteoriteId)
                .orElseThrow(()->new ObjectNotFoundException("meteorite", String.valueOf(meteoriteId)));
    }

    public List<Meteorite> findAll() {
        return this.meteoriteRepository.findAll();
    }
}
