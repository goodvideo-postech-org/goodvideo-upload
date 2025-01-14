package com.goodvideo.upload.usecase;

import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoMensageriaGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnviarParaProcessamentoImpl implements EnviarParaProcessamento {
  
  private final ProcessamentoMensageriaGateway processamentoMensageria;
  
  @Override
  public void executar(Processamento processamento) {
    processamentoMensageria.publicar(processamento);
  }

}
