package com.esi.mspatientmysql.Controller;



import com.esi.mspatientmysql.DTO.OrdonnanceDTO;
import com.esi.mspatientmysql.Entities.Patient;
import com.esi.mspatientmysql.Proxy.OrdonnanceProxy;
import com.esi.mspatientmysql.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
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
    final OrdonnanceProxy ordonnanceProxy;

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
