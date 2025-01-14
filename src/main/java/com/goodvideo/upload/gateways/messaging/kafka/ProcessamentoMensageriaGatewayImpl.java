package com.goodvideo.upload.gateways.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoMensageriaGateway;
import com.goodvideo.upload.gateways.messaging.kafka.resources.ProcessamentoMensagem;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcessamentoMensageriaGatewayImpl implements ProcessamentoMensageriaGateway {

  private final KafkaTemplate<String, String> kafkaTemplate;
  
  @Override
  public void publicar(final Processamento processamento) {
    kafkaTemplate.send(Topic.PROCESSAMENTO_VIDEO.getDescription(), new Gson().toJson(new ProcessamentoMensagem(processamento)));
  }

}
