package com.goodvideo.upload.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.ProcessamentoRequisicao;
import com.goodvideo.upload.domains.UsuarioToken;

@ExtendWith(MockitoExtension.class)
public class ProcessamentoVideoImplTest {

  @InjectMocks
  private ProcessamentoVideoImpl provider;
  @Mock
  private SalvarProcessamento salvarProcessamento;
  @Mock
  private SalvarArquivo salvarArquivo;
  @Mock
  private EnviarParaProcessamento enviarParaProcessamento;
  @Mock
  private ValidarToken validarToken;
  @Mock
  private ValidarFormatoTamanho validarFormatoTamanho;
  
  @Test
  public void deveProcessarRequisicao() {
    final ProcessamentoRequisicao processamentoRequisicao = 
        new ProcessamentoRequisicao(null, "file.mp4", UUID.randomUUID().toString(), "email@gmail.com");
    
    final Processamento p = new Processamento();
    p.setId(UUID.randomUUID().toString());
    
    UsuarioToken uToken = new UsuarioToken(UUID.randomUUID().toString(), "email@gmail.com");
    when(validarToken.executar(anyString())).thenReturn(uToken);
    when(salvarArquivo.executar(any(), anyString())).thenReturn(UUID.randomUUID().toString());
    when(salvarProcessamento.executar(anyString(), any())).thenReturn(p);
    
    final String idProcessamento = provider.execute(UUID.randomUUID().toString(), processamentoRequisicao);
    
    assertEquals(idProcessamento, p.getId());
    verify(validarToken).executar(anyString());
    verify(salvarProcessamento).executar(anyString(), any(ProcessamentoRequisicao.class));
    verify(enviarParaProcessamento).executar(p);
    verify(salvarArquivo).executar(any(), any());    
  }
  
}
