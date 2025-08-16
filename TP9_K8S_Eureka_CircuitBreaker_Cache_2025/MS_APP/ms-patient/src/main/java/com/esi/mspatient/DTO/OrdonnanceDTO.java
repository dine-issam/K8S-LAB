package com.esi.mspatient.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrdonnanceDTO {
    private Long idOrdonnance;
    private List<MedicamentInfo> medicaments;

}


