package com.esi.msordonnancemongo.Proxy;

import com.esi.msordonnancemongo.DTO.RemboursementDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-remboursement")
@LoadBalancerClient(name="ms-remboursement")
public interface RemboursementProxy {
    @GetMapping("/remboursements/{idr}")
    public RemboursementDTO getReboursement(@PathVariable("idr") Long idr,
                                            @RequestParam("projection") String projection);
}
