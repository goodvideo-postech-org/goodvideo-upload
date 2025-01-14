package com.goodvideo.upload.usecase;

import com.goodvideo.upload.domains.Processamento;

public interface EnviarParaProcessamento {

  void executar(final Processamento processamento);
  
}
