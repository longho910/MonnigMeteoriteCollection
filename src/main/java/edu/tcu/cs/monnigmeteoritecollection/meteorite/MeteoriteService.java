package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Meteorite save(Meteorite newMeteorite) {
        return this.meteoriteRepository.save(newMeteorite);
    }

    public Meteorite update(String meteoriteId, Meteorite update) {
        return this.meteoriteRepository.findById(Long.valueOf(meteoriteId))
                .map(oldMeteorite -> {
                    oldMeteorite.setName(update.getName());
                    oldMeteorite.setMonnigNumber(update.getMonnigNumber());
                    oldMeteorite.setCountry(update.getCountry());
                    oldMeteorite.set_group(update.get_group());
                    oldMeteorite.set_class(update.get_class());
                    oldMeteorite.setHowFound(update.getHowFound());
                    oldMeteorite.setWeight(update.getWeight());
                    oldMeteorite.setYearFound(update.getYearFound());

                    return this.meteoriteRepository.save(oldMeteorite);
                })
                .orElseThrow(() -> new ObjectNotFoundException("meteorite", meteoriteId));
    }

    public void delete(String meteoriteId) {
        this.meteoriteRepository.findById(Long.valueOf(meteoriteId))
                .orElseThrow(() -> new ObjectNotFoundException("meteorite", meteoriteId));
        this.meteoriteRepository.deleteById(Long.valueOf(meteoriteId));
    }

    public Meteorite saveSub(String meteoriteId, Meteorite newMeteorite) {
        Optional<Meteorite> parentMeteorite = meteoriteRepository.findById(Long.valueOf(meteoriteId));

        return parentMeteorite.map(parent -> {
            // Create a new meteorite object, copying from the parent
            Meteorite subMeteorite = new Meteorite();
            subMeteorite.setName(parent.getName());
            subMeteorite.setMonnigNumber(newMeteorite.getMonnigNumber()); // New Monnig Number
            subMeteorite.setCountry(parent.getCountry());
            subMeteorite.set_class(parent.get_class());
            subMeteorite.set_group(parent.get_group());
            subMeteorite.setYearFound(parent.getYearFound());
            subMeteorite.setWeight(newMeteorite.getWeight()); // New weight
            subMeteorite.setHowFound(parent.getHowFound());
            subMeteorite.setLoan(parent.getLoan());

            // Save the new subsample in the repository
            return meteoriteRepository.save(subMeteorite);
        }).orElseThrow(() -> new ObjectNotFoundException("meteorite", meteoriteId));
    }
}
