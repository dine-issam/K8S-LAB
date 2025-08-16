package com.esi.mspatientmysql.Entities;

import com.esi.mspatientmysql.DTO.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;

    private String nom;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<AntecedentMedical> antecedentsMedicaux;


    @Transient
    private List<OrdonnanceDTO> ordonnances;
}
