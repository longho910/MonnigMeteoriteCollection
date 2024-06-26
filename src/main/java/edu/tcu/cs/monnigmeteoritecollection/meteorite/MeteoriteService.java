package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    // use case 16
    public List<Meteorite> findAllOnLoan() {
        List<Meteorite> meteoriteList = this.meteoriteRepository.findAll();
        List<Meteorite> truncatedList = new ArrayList<>();
        for (Meteorite elem : meteoriteList) {
            // remove all meteorites with Loan == null or empty
            if (elem.getLoan() != null) {
                truncatedList.add(elem);
            }
        }
        return truncatedList;
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
                    oldMeteorite.set_class(update.get_class());
                    oldMeteorite.set_group(update.get_group());
                    oldMeteorite.setYearFound(update.getYearFound());
                    oldMeteorite.setWeight(update.getWeight());

                    oldMeteorite.setHowFound(update.getHowFound());
                    oldMeteorite.setSampleHistory(update.getSampleHistory());
                    oldMeteorite.setLoan(update.getLoan());

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


    public Page<Meteorite> findByCriteria(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<Meteorite> spec = Specification.where(null);

        if (StringUtils.hasLength(searchCriteria.get("monnigNumber"))) {
            spec = spec.and(MeteoriteSpecs.hasMonnigNumber(searchCriteria.get("monnigNumber")));
        }

        if (StringUtils.hasLength(searchCriteria.get("name"))) {
            spec = spec.and(MeteoriteSpecs.containsName(searchCriteria.get("name")));
        }

        if (StringUtils.hasLength(searchCriteria.get("_class"))) {
            spec = spec.and(MeteoriteSpecs.containsClass(searchCriteria.get("_class")));
        }


        if (StringUtils.hasLength(searchCriteria.get("_group"))) {
            spec = spec.and(MeteoriteSpecs.containsGroup(searchCriteria.get("_group")));
        }


        if (StringUtils.hasLength(searchCriteria.get("country"))) {
            spec = spec.and(MeteoriteSpecs.containsCountry(searchCriteria.get("country")));
        }

        if (StringUtils.hasLength(searchCriteria.get("howFound"))) {
            spec = spec.and(MeteoriteSpecs.hasHowFound(searchCriteria.get("howFound")));
        }

        return this.meteoriteRepository.findAll(spec, pageable);
    }

    public Page<Meteorite> findAll(Pageable pageable) {
        return this.meteoriteRepository.findAll(pageable);

    }
}
