package com.esi.msordonnancemongo.Entities;

import com.esi.msordonnancemongo.DTO.PatientDTO;
import com.esi.msordonnancemongo.DTO.RemboursementDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data @AllArgsConstructor
@NoArgsConstructor
public class Ordonnance {
    @Id
    private Long idOrdonnance;

    private Date dateOrdonnance;


    @ToString.Exclude
    @JsonIgnore
    @DBRef
    private List<Medicament> medicaments;

    private Long idPatient;
    private Long idRemboursement;

    @Transient
    private PatientDTO patient;

    @Transient
    private RemboursementDTO remboursement;
}
