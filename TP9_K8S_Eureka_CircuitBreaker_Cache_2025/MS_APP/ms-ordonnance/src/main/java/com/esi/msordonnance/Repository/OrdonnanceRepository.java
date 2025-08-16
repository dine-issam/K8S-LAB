package com.esi.msordonnance.Repository;

import com.esi.msordonnance.Entities.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {

    List<Ordonnance> findOrdonnancesByIdPatient(long idPatient);
}
