package edu.tcu.cs.monnigmeteoritecollection.meteorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface MeteoriteRepository extends JpaRepository<Meteorite, Long>, JpaSpecificationExecutor<Meteorite> {


}
