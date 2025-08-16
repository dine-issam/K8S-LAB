package com.esi.msordonnancemongo.Repository;

import com.esi.msordonnancemongo.Entities.Medicament;
import feign.Param;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MedicamentRepository
        extends MongoRepository<Medicament, Long> {

    @Query("{ 'ordonnance.idPatient' :#{#idPatient} }")
    List<Medicament> findMedicamentsByIdPatient(@Param("idPatient") Long idPatient);
}
