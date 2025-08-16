package com.esi.mspatientmysql.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AntecedentMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedent;

    private String maladie;

    private Date dateDiagnostic;

    @ManyToOne
    private Patient patient;
}
