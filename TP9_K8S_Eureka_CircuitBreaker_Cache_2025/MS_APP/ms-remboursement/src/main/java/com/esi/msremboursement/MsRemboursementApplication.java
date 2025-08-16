package com.esi.msremboursement;

import com.esi.msremboursement.Entities.Assure;
import com.esi.msremboursement.Entities.Remboursement;
import com.esi.msremboursement.Repository.AssureRepository;
import com.esi.msremboursement.Repository.RemboursementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
public class MsRemboursementApplication implements CommandLineRunner {
     final RemboursementRepository remboursementRepository;
     final AssureRepository assureRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsRemboursementApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Assure a1=assureRepository.save(new Assure(1L,"99774422",2000D,null));
        Assure a2=assureRepository.save(new Assure(2L,"62365525",6000D,null));

        remboursementRepository.save(new Remboursement(null,new Date(), 540D , a2,2L));
        remboursementRepository.save(new Remboursement(null,new Date(), 700D , a1,1L));
    }
}
