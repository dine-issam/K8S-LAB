package com.esi.mspatientmysql.Repository;

import com.esi.mspatientmysql.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
