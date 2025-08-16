package com.esi.mspatientmysql.Repository;

import com.esi.mspatientmysql.Entities.AntecedentMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentRepository extends JpaRepository<AntecedentMedical, Long> {
}
