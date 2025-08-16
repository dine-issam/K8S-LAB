package com.esi.msordonnance.Controller;

import com.esi.msordonnance.DTO.PatientDTO;
import com.esi.msordonnance.Entities.Ordonnance;
import com.esi.msordonnance.Proxy.PatientProxy;
import com.esi.msordonnance.Proxy.RemboursementProxy;
import com.esi.msordonnance.Repository.OrdonnanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrdonnanceAPI {

    final OrdonnanceRepository ordonnanceRepository;
    final PatientProxy patientProxy;
    final RemboursementProxy remboursementProxy;


    @GetMapping("ordonnance/{ido}")
    public Ordonnance getOrdonnance(@PathVariable("ido") Long ido) {
      Ordonnance ordonnance=ordonnanceRepository.findById(ido).get();

      ordonnance.setPatient(patientProxy.getPatient(ordonnance.getIdPatient()));

      ordonnance.setRemboursement(remboursementProxy.getReboursement(
                                                                    ordonnance.getIdRemboursement(),
                                                           "toOrd"));

      System.out.println(ordonnance);


      return ordonnance;
    }

    @GetMapping("ordonnance2/{ido}")
    public ResponseEntity<Ordonnance> getOrdonnance2(@PathVariable("ido") Long ido) {
        // Use Optional to handle the case where the ordonnance might not be found
        Optional<Ordonnance> ordonnanceOptional = ordonnanceRepository.findById(ido);

        if (ordonnanceOptional.isPresent()) {
            Ordonnance ordonnance = ordonnanceOptional.get();

            // Fetch the patient DTO using the patientProxy
            PatientDTO patient = patientProxy.getPatient(ordonnance.getIdPatient());

            // Check if the patient is not null before setting it
            if (patient != null) {
                ordonnance.setPatient(patient);
            } else {
                // Handle the case where the patient is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(ordonnance);
        } else {
            // Handle the case where the ordonnance is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
   }
