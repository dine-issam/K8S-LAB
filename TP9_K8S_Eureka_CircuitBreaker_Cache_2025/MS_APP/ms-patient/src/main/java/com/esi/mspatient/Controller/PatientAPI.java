package com.esi.mspatient.Controller;


import com.esi.mspatient.DTO.OrdonnanceDTO;
import com.esi.mspatient.Entities.Patient;
import com.esi.mspatient.Proxy.OrdonnanceProxy;
import com.esi.mspatient.Repository.PatientRepository;
import feign.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PatientAPI {

    final PatientRepository patientRepository;
    final OrdonnanceProxy  ordonnanceProxy;

    @GetMapping("patient/{idp}")
    public Patient getPatientWithOrdonannce(@PathVariable("idp") Long idPatient)
    {
        Patient patient=patientRepository.findById(idPatient)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        List<OrdonnanceDTO> ordonnances=new ArrayList<>(ordonnanceProxy.findOrdonnancesByIdPatient(idPatient,"toPatient").getContent());

        patient.setOrdonnances(ordonnances);
        return patient;
    }
}
