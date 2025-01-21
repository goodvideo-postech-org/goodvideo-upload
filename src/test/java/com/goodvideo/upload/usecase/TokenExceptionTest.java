package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenExceptionTest {

  @InjectMocks
  private TokenException provider;
  
  @Test
  public void deveRetornarInstanciasDeErro() {
    
    TokenException tokenException = new TokenException("Token exception");
    assertEquals("Token exception", tokenException.getMessage());
    assertNull(tokenException.getCause());
    
    TokenException tokenExceptionNullpointer = new TokenException("Token exception com nullpointer", new NullPointerException());
    assertEquals("Token exception com nullpointer", tokenExceptionNullpointer.getMessage());
    assertTrue(tokenExceptionNullpointer.getCause() instanceof NullPointerException);
  }
  
}
