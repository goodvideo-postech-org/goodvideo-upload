package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidarArquivoExceptionTest {

  @InjectMocks
  private ValidarArquivoExpcetion provider;
  
  @Test
  public void deveRetornarInstanciasDeErro() {
    
    ValidarArquivoExpcetion salvarArquivoException = new ValidarArquivoExpcetion("Erro ao validar arquivo");
    assertEquals("Erro ao validar arquivo", salvarArquivoException.getMessage());
    assertNull(salvarArquivoException.getCause());
    
    ValidarArquivoExpcetion salvarArquivoException2 = new ValidarArquivoExpcetion("Erro ao validar arquivo com nullpointer", new NullPointerException());
    assertEquals("Erro ao validar arquivo com nullpointer", salvarArquivoException2.getMessage());
    assertTrue(salvarArquivoException2.getCause() instanceof NullPointerException);
  }
  
}
