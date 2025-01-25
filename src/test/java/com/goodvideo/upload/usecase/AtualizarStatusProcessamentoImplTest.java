package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoStatus;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.Status;
import joptsimple.internal.Strings;
import lombok.RequiredArgsConstructor;

@ExtendWith(MockitoExtension.class)
public class AtualizarStatusProcessamentoImplTest {

  @InjectMocks
  private AtualizarStatusProcessamentoImpl provider;
  
  @Mock
  private EnviarEmail enviarEmail;
  
  @Mock
  private ProcessamentoDatabaseGateway processamentoDatabaseGateway;
  
  @Captor
  private ArgumentCaptor<Processamento> processamentoSalvo;
  
  @Test
  public void deveSalvarAtualizacaoProcessamento() {
    ReflectionTestUtils.setField(provider, "baseUrl", "/field/source");
    
    final String idVideo = UUID.randomUUID().toString();
    final Processamento processamento = Processamento.builder()
      .id(idVideo)
      .email("email@gmail.com")
      .diretorio("/path/video.mp4")
      .diretorioZip(Strings.EMPTY)
      .dataCriacao(LocalDateTime.now())
    .build();
    
    when(processamentoDatabaseGateway.obterPorId(idVideo)).thenReturn(processamento);
    
    final ProcessamentoStatus pStatus = new ProcessamentoStatus(idVideo, "video/video.zip", "CONCLUIDO");
    
    provider.atualizar(pStatus);
    
    verify(processamentoDatabaseGateway).salvar(processamentoSalvo.capture());
    verifyNoInteractions(enviarEmail);
    assertEquals(Status.CONCLUIDO, processamentoSalvo.getValue().getStatus());
    assertNotNull(processamentoSalvo.getValue().getDataModificacao());
  }
  
  @Test
  public void deveSalvarAtualizacaoProcessamentoEEnviarEmail() {
    ReflectionTestUtils.setField(provider, "baseUrl", "/field/source");
    
    final String idVideo = UUID.randomUUID().toString();
    final Processamento processamento = Processamento.builder()
      .id(idVideo)
      .email("email@gmail.com")
      .diretorio("/path/video.mp4")
      .diretorioZip(Strings.EMPTY)
      .dataCriacao(LocalDateTime.now())
    .build();
    
    when(processamentoDatabaseGateway.obterPorId(idVideo)).thenReturn(processamento);
    
    final ProcessamentoStatus pStatus = new ProcessamentoStatus(idVideo, "video/video.zip", "ERRO");
    
    provider.atualizar(pStatus);
    
    verify(processamentoDatabaseGateway).salvar(processamentoSalvo.capture());
    verify(enviarEmail).enviar(idVideo, processamento.getEmail());
    assertEquals(Status.ERRO, processamentoSalvo.getValue().getStatus());
    assertNotNull(processamentoSalvo.getValue().getDataModificacao());
  }
  
}
