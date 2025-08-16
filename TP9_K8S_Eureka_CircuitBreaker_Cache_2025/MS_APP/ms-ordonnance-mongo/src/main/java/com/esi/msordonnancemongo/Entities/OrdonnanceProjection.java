package com.esi.msordonnancemongo.Entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "toPatient", types = {Ordonnance.class})
public interface OrdonnanceProjection {

    Long getIdOrdonnance();

    //@Value("#{target.medicaments.![{nom: nom, cout: cout}]}")
    List<MedicamentInfo> getMedicaments();

    interface MedicamentInfo {
         String getNom();
        double getCout();
    }
}
