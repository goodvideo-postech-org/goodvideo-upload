package com.goodvideo.upload.usecase;

import java.util.List;
import org.springframework.stereotype.Component;
import com.goodvideo.upload.domains.Processamento;
import com.goodvideo.upload.domains.UsuarioToken;
import com.goodvideo.upload.gateways.ProcessamentoDatabaseGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ListarProcessamentoVideoImpl implements ListarProcessamentoVideo {

  private final ValidarToken validarToken;
  private final ProcessamentoDatabaseGateway processamentoDatabaseGateway;
  
  @Override
  public List<Processamento> listar(String auth) {
    final UsuarioToken usuarioToken = validarToken.executar(auth);
    
    return processamentoDatabaseGateway.obterProcessamentosPorIdUsuario(usuarioToken.getId());
  }

}
