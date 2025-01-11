package com.goodvideo.upload.gateways;

import com.goodvideo.upload.domains.Processamento;

public interface ProcessamentoDatabaseGateway {

  Processamento salvar(Processamento processamento);
  
}
