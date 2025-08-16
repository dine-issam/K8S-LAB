package com.esi.mspatient.Repository;

import com.esi.mspatient.Entities.AntecedentMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentRepository extends JpaRepository<AntecedentMedical, Long> {
}
