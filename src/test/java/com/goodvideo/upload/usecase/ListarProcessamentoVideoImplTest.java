package com.goodvideo.upload.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.UsuarioToken;
import com.goodvideo.upload.gateways.database.ProcessamentoDatabaseGatewayImpl;

@ExtendWith(MockitoExtension.class)
public class ListarProcessamentoVideoImplTest {

  @InjectMocks
  private ListarProcessamentoVideoImpl provider;
  
  @Mock
  private ValidarToken validarToken;
  
  @Mock
  private ProcessamentoDatabaseGatewayImpl processamentoDatabaseGatewayImpl;
  
  @Test
  public void deveRetornarListaProcessamento() {
    final UsuarioToken usuarioToken = new UsuarioToken();
    usuarioToken.setEmail("bridi@bridi.com.br");
    usuarioToken.setId(UUID.randomUUID().toString());
    
    when(validarToken.executar("auth token")).thenReturn(usuarioToken);
    
    final Processamento p1 = Processamento.builder().email(usuarioToken.getEmail()).idUsuario(usuarioToken.getId()).id(UUID.randomUUID().toString()).build();
    final Processamento p2 = Processamento.builder().email(usuarioToken.getEmail()).idUsuario(usuarioToken.getId()).id(UUID.randomUUID().toString()).build();
    
    final List<Processamento> processamentos = Arrays.asList(p1, p2);
    
    when(processamentoDatabaseGatewayImpl.obterProcessamentosPorIdUsuario(usuarioToken.getId())).thenReturn(processamentos);
    
    final List<Processamento> listar = provider.listar("auth token");
    
    assertEquals(2, listar.size());
  }  
  
}
