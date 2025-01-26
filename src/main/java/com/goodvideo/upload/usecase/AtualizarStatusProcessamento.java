package com.goodvideo.upload.usecase;

import com.goodvideo.upload.domains.ProcessamentoStatus;

public interface AtualizarStatusProcessamento {

  void atualizar(ProcessamentoStatus processamentoStatus);
  
}
