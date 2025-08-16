package com.esi.msordonnancemongo.Controller;

import com.esi.msordonnancemongo.DTO.PatientDTO;
import com.esi.msordonnancemongo.Entities.Ordonnance;
import com.esi.msordonnancemongo.Proxy.PatientProxy;
import com.esi.msordonnancemongo.Proxy.RemboursementProxy;
import com.esi.msordonnancemongo.Repository.OrdonnanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
