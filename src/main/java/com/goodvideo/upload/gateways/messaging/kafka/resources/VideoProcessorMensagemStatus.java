package com.goodvideo.upload.gateways.messaging.kafka.resources;

import java.io.Serializable;
import com.goodvideo.upload.domains.ProcessamentoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoProcessorMensagemStatus implements Serializable {
  
  private static final long serialVersionUID = -8971087709475528404L;
  
  private String idVideo;
  private String status;
  private String diretorio;
  
  public ProcessamentoStatus toDomain() {
    return ProcessamentoStatus.builder()
      .idVideo(this.idVideo)
      .status(this.status)
      .diretorio(diretorio)
    .build();
  }

}
