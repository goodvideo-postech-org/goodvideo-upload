package com.goodvideo.upload.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.UsuarioToken;

@ExtendWith(MockitoExtension.class)
public class ValidarTokenImplTest {
  
  @InjectMocks
  private ValidarTokenImpl provider;
  
  @Test
  public void deveValidarToken() {
    
    final String authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnb29kdmlkZW8tYXV0aCIsInN1YiI6IntcImlkXCI6XCIwNWJlZDA1OC04ZjgyLTQ4ZGYtOWQ5Ni02NTg5N2ZmNmUzZWRcIixcImVtYWlsXCI6XCJlbWFpbEBnbWFpbC5jb21cIn0iLCJleHAiOjI5MzY4MDgyNzd9._qE5idgumjPyZL9hDNeJOxJ9v8MZX8OVj6zWxzygnYg";
    
    final UsuarioToken executar = provider.executar(authToken);
    
    assertEquals("05bed058-8f82-48df-9d96-65897ff6e3ed", executar.getId());
    assertEquals("email@gmail.com", executar.getEmail());
        
  }

}
