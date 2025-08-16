package com.esi.mspatientmysql.Proxy;
import com.esi.mspatientmysql.DTO.*;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-ordonnance")
@LoadBalancerClient(name = "ms-ordonnance")
public interface OrdonnanceProxy {

    @GetMapping("/ordonnances/search/findOrdonnancesByIdPatient")
    CollectionModel<OrdonnanceDTO> findOrdonnancesByIdPatient(@RequestParam("idPatient") Long idPatient,
                                                              @RequestParam("projection") String projection);
}
