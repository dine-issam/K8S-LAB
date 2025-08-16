package com.esi.msordonnance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMed;

    private String nom;
    private int duree;
    private String dosage;
    private double cout;

    @ManyToOne

    private Ordonnance ordonnance;

}
