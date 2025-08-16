package com.esi.msremboursement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Assure {
    @Id
    private Long idAssure;

    private  String numeroSecuriteSocial;
    private Double plafondRemboursement;

    @JsonIgnore
    @OneToMany(mappedBy = "assure", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;
}
