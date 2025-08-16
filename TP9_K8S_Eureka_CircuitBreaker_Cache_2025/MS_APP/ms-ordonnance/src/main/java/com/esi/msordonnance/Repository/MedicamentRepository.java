package com.esi.msordonnance.Repository;

import com.esi.msordonnance.Entities.Medicament;
import com.esi.msordonnance.Entities.Ordonnance;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    @Query("select m from Medicament m where m.ordonnance.idPatient= :idPatient")
    List<Medicament> findMedicamentsByIdPatient(@Param("idPatient") Long idPatient);
}
