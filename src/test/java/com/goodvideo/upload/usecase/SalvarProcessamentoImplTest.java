package com.goodvideo.upload.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.InputStream;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import com.goodvideo.upload.gateways.database.entity.Status;

@ExtendWith(MockitoExtension.class)
public class SalvarProcessamentoImplTest {

  @InjectMocks
  private SalvarProcessamentoImpl provider;
  
  @Mock
  private ProcessamentoDatabaseGateway processamentoDatabaseGateway;
  
  @Captor
  private ArgumentCaptor<Processamento> processamentoCapture;
  
  @Test
  public void deveSalvarProcessamento() {

    ReflectionTestUtils.setField(provider, "defaultPath", "/usr/share");
    
    final InputStream resourceAsStream = this.getClass().getResourceAsStream("/sample.txt");
    final String uuidUsuario = UUID.randomUUID().toString();
    final String emailUsuario = "bridi@gmail.com";
    
    final ProcessamentoRequisicao p = new ProcessamentoRequisicao(resourceAsStream, "sample.txt", uuidUsuario, emailUsuario);
    final String idVideo = UUID.randomUUID().toString();
    final Processamento executar = provider.executar(idVideo, p);
    final String diretorio = String.format("%s/%s/%s.mp4", "/usr/share", uuidUsuario, idVideo);
    
    verify(processamentoDatabaseGateway).salvar(processamentoCapture.capture());
    
    final Processamento processamentoSalvo = processamentoCapture.getValue();
    
    assertEquals(Status.PROCESSANDO, processamentoSalvo.getStatus());
    assertEquals(idVideo, processamentoSalvo.getId());
    assertEquals(emailUsuario, processamentoSalvo.getEmail());
    assertEquals(diretorio, processamentoSalvo.getDiretorio());
  }
  
}
