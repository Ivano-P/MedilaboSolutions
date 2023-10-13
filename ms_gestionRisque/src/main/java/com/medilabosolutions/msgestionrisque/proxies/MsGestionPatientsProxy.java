package com.medilabosolutions.msgestionrisque.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="MsGestionPatients", url = "localhost:9002")
public interface MsGestionPatientsProxy {


}
