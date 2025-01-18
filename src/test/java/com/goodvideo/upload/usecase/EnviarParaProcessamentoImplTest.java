package com.goodvideo.upload.usecase;

import static org.mockito.Mockito.verify;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoMensageriaGateway;

@ExtendWith(MockitoExtension.class)
public class EnviarParaProcessamentoImplTest {

  @InjectMocks
  EnviarParaProcessamentoImpl provider;
  
  @Mock
  ProcessamentoMensageriaGateway processamentoMensageria;
  
  @Test
  void deveEnviarMensagem() {
    Processamento processamento = Processamento.builder()
          .id(UUID.randomUUID().toString())
          .email("bridi@bridi.com")
          .diretorio("/path/path")
        .build();
    
    provider.executar(processamento);
    
    verify(processamentoMensageria).publicar(processamento);
  }
  
}
