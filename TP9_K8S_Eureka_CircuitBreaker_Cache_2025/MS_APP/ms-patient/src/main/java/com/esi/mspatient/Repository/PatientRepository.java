package com.esi.mspatient.Repository;

import com.esi.mspatient.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
