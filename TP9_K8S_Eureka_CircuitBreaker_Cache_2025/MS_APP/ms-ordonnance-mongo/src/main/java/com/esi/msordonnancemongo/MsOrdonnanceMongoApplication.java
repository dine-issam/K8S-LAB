package com.esi.msordonnancemongo;

import com.esi.msordonnancemongo.Entities.Medicament;
import com.esi.msordonnancemongo.Entities.Ordonnance;
import com.esi.msordonnancemongo.Repository.MedicamentRepository;
import com.esi.msordonnancemongo.Repository.OrdonnanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@RequiredArgsConstructor
public class MsOrdonnanceMongoApplication implements CommandLineRunner {

    final OrdonnanceRepository ordonnanceRepository;
    final MedicamentRepository medicamentRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsOrdonnanceMongoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Ordonnance o1 = new Ordonnance(1L, new Date(), null, 1L,2L,null,null);
        Ordonnance o2 = new Ordonnance(2L, new Date(), null, 2L,1L,null,null);
        o1=ordonnanceRepository.save(o1);
        o2=ordonnanceRepository.save(o2);

        medicamentRepository.save(new Medicament(1L, "solipred", 5, "20mg",500,o1));
        medicamentRepository.save(new Medicament(2L, "donicort", 15, "64mg",650,o1));

        medicamentRepository.save(new Medicament(3L, "augmentin", 7, "1000mg",800,o2));
        medicamentRepository.save(new Medicament(4L, "Ebasta", 30, "10mg",750,o2));
    }

}

