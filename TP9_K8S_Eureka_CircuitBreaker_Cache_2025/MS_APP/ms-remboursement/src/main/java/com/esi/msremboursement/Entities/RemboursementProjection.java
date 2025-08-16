package com.esi.msremboursement.Entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "toOrd", types = {Remboursement.class})
public interface RemboursementProjection {

    Double getMontant();

    @Value("#{target.assure.numeroSecuriteSocial}")
    String getNSS();
}
