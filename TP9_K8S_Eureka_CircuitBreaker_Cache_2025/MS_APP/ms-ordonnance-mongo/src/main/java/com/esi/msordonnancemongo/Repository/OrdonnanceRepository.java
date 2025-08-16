package com.esi.msordonnancemongo.Repository;

import com.esi.msordonnancemongo.Entities.Ordonnance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrdonnanceRepository extends MongoRepository<Ordonnance, Long> {

    List<Ordonnance> findOrdonnancesByIdPatient(long idPatient);
}
