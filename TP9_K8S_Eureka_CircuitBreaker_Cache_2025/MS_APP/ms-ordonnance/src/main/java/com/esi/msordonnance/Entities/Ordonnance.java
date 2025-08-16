package com.esi.msordonnance.Entities;

import com.esi.msordonnance.DTO.PatientDTO;
import com.esi.msordonnance.DTO.RemboursementDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdonnance;

    private Date dateOrdonnance;


    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL)
    private List<Medicament> medicaments;

    private Long idPatient;
    private Long idRemboursement;

    @Transient
    private PatientDTO patient;

    @Transient
    private RemboursementDTO remboursement;
}
