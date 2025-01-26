package com.goodvideo.upload.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.amazonaws.services.s3.AmazonS3;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import joptsimple.internal.Strings;

@ExtendWith(MockitoExtension.class)
public class RemoverArquivoZipImplTest {

  @InjectMocks
  private RemoverArquivoZipImpl provider;
  
  @Mock
  private ProcessamentoDatabaseGateway processamentoDatabase;
  
  @Mock
  private AmazonS3 amazonS3;
  
  @Test
  public void deveDeletarArquivos() {
    
    final Processamento processamento = Processamento.builder()
        .id(UUID.randomUUID().toString())
        .email("email@gmail.com")
        .diretorio("/path/video.mp4")
        .diretorioZip(Strings.EMPTY)
        .dataCriacao(LocalDateTime.now())
      .build();
      
    when(processamentoDatabase.obterProcessamentosDataLimite(any())).thenReturn(List.of(processamento));
    
    provider.remover();
    
    verify(processamentoDatabase).salvar(any());    
  }
  
}
