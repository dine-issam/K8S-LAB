package com.esi.msordonnancemongo.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicament {

    @Id
    private Long idMed;

    private String nom;
    private int duree;
    private String dosage;
    private double cout;

    @DBRef
    private Ordonnance ordonnance;

}
