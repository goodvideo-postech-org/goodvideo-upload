package com.goodvideo.upload.gateways;

import com.goodvideo.upload.domains.Processamento;

public interface ProcessamentoMensageriaGateway {
  void publicar(final Processamento processamento);
}
