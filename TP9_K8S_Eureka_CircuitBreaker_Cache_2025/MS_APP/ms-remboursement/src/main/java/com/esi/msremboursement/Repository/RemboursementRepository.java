package com.esi.msremboursement.Repository;

import com.esi.msremboursement.Entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}
