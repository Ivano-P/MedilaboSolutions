package com.medilabosolutions.msgestionrisque.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="MsGestionHistorique", url = "localhost:9003")
public interface MsGestionHistoriqueProxy {

}
