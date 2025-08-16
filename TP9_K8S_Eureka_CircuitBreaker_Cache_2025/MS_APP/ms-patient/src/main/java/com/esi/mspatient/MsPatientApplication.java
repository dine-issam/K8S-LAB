package com.esi.mspatient;

import com.esi.mspatient.Entities.AntecedentMedical;
import com.esi.mspatient.Entities.Patient;
import com.esi.mspatient.Repository.AntecedentRepository;
import com.esi.mspatient.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
@EnableFeignClients
public class MsPatientApplication implements CommandLineRunner {

    final PatientRepository patientRepository;
    final AntecedentRepository antecedentRepository;

    final RepositoryRestConfiguration config;

    public static void main(String[] args) {
        SpringApplication.run(MsPatientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        config.exposeIdsFor(Patient.class);

        Patient p1=patientRepository.save(new Patient(null, "said", null,null));
        Patient p2=patientRepository.save(new Patient(null, "karim", null,null));

        antecedentRepository.save(new AntecedentMedical(null,"Asthm",new Date(),p1));
        antecedentRepository.save(new AntecedentMedical(null,"Diabete",new Date(),p1));

        antecedentRepository.save(new AntecedentMedical(null,"grippe",new Date(),p2));
    }
}
