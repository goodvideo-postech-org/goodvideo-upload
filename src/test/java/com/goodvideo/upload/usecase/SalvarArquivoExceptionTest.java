package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalvarArquivoExceptionTest {

  @InjectMocks
  private SalvarArquivoException provider;
  
  @Test
  public void deveRetornarInstanciasDeErro() {
    
    SalvarArquivoException salvarArquivoException = new SalvarArquivoException("Erro ao salvar arquivo");
    assertEquals("Erro ao salvar arquivo", salvarArquivoException.getMessage());
    assertNull(salvarArquivoException.getCause());
    
    SalvarArquivoException salvarArquivoException2 = new SalvarArquivoException("Erro ao salvar arquivo com nullpointer", new NullPointerException());
    assertEquals("Erro ao salvar arquivo com nullpointer", salvarArquivoException2.getMessage());
    assertTrue(salvarArquivoException2.getCause() instanceof NullPointerException);
  }
  
}
