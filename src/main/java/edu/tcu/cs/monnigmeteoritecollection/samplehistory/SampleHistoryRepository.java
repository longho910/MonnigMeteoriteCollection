package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SampleHistoryRepository extends JpaRepository<SampleHistory, Long>{
    
}
