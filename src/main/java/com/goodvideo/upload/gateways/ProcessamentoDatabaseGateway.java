package com.goodvideo.upload.gateways;

import java.util.List;
import com.goodvideo.upload.domains.Processamento;

public interface ProcessamentoDatabaseGateway {

  Processamento obterPorId(final String idProcessamento);
   
  Processamento salvar(Processamento processamento);
  
  List<Processamento> obterProcessamentosPorIdUsuario(final String idUsuario);
  
}
