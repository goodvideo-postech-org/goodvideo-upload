package com.goodvideo.upload.gateways.messaging.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.gateways.messaging.kafka.resources.VideoProcessorMensagemStatus;
import com.goodvideo.upload.usecase.AtualizarStatusProcessamento;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VideoProcessorStatusListener {

  private final AtualizarStatusProcessamento atualizarStatusProcessamento;
  
  @KafkaListener(topics = {"com.goodvideo.upload.finalizar_processamento.v1"})
  public void getMessage(String message) {
    
    final VideoProcessorMensagemStatus videoProcessamento = new Gson().fromJson(message, VideoProcessorMensagemStatus.class);
    
    try {
      atualizarStatusProcessamento.atualizar(videoProcessamento.toDomain());
    }catch(Exception e) {
      System.err.println("Erro ao atualizar status.");
    }

  }
  
}
