package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<SampleHistory, Integer> {

}
