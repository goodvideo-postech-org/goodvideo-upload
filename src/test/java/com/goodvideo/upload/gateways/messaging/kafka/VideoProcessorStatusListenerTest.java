package com.goodvideo.upload.gateways.messaging.kafka;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.ProcessamentoStatus;
import com.goodvideo.upload.gateways.messaging.kafka.resources.VideoProcessorMensagemStatus;
import com.goodvideo.upload.usecase.AtualizarStatusProcessamentoImpl;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
public class VideoProcessorStatusListenerTest {

  @InjectMocks
  private VideoProcessorStatusListener provider;
  
  @Mock
  private AtualizarStatusProcessamentoImpl atualizarStatus;
  
  @Test
  public void deveAtualizarStatus() {
    
    VideoProcessorMensagemStatus vp = new VideoProcessorMensagemStatus(UUID.randomUUID().toString(), "CONCLUIDO", "video/zip.zip");
    
    String json = new Gson().toJson(vp);
    
    provider.ler(json);
    
    verify(atualizarStatus).atualizar(any(ProcessamentoStatus.class));
  }
  
}
